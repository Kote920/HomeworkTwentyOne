package com.example.homeworktwentyone.presentation.fragments

import com.example.homeworktwentyone.data.common.Resource
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homeworktwentyone.databinding.FragmentProductsBinding
import com.example.homeworktwentyone.presentation.adapters.ProductsRecyclerAdapter
import com.example.homeworktwentyone.presentation.base.BaseFragment
import com.example.homeworktwentyone.presentation.viewModels.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : BaseFragment<FragmentProductsBinding>(FragmentProductsBinding::inflate) {

    private  val viewModel: ProductsViewModel by viewModels()
    private lateinit var adapter: ProductsRecyclerAdapter

    override fun setUp() {
        setUpRecycler()
        viewModel.getProductList()
        bindObserves()

    }

    override fun listeners() {

    }

    private fun setUpRecycler(){
        adapter = ProductsRecyclerAdapter{
            openProductDetails(it.title, it.price)
        }
        binding.productsRecycler.layoutManager = GridLayoutManager(requireContext(),2)
        binding.productsRecycler.adapter = adapter

    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.productUIListFlow.collect(){
                    when(it){

                        is Resource.Loading -> {
                            binding.pbProducts.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.pbProducts.visibility = View.GONE

                            adapter.submitList(it.responseData)

                        }
                        is Resource.Failed -> {
                            binding.pbProducts.visibility = View.GONE
                            val errorMessage = it.message
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT)
                                .show()

                        }

                    }
                }
            }
        }
    }

    private fun openProductDetails(title: String, price: String){
        findNavController().navigate(ProductsFragmentDirections.actionProductsFragmentToProductDetailFragment(title, price))
    }

}