package com.example.homeworktwentyone.presentation.fragments

import android.util.Log.d
import com.example.homeworktwentyone.data.common.Resource
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworktwentyone.databinding.FragmentProductsBinding
import com.example.homeworktwentyone.presentation.adapters.CategoriesRecyclerAdapter
import com.example.homeworktwentyone.presentation.adapters.ProductsRecyclerAdapter
import com.example.homeworktwentyone.presentation.base.BaseFragment
import com.example.homeworktwentyone.presentation.model.Category
import com.example.homeworktwentyone.presentation.model.ProductUI
import com.example.homeworktwentyone.presentation.viewModels.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : BaseFragment<FragmentProductsBinding>(FragmentProductsBinding::inflate) {

    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var productsAdapter: ProductsRecyclerAdapter
    private lateinit var categoriesAdapter: CategoriesRecyclerAdapter
    private  lateinit var productsList: List<ProductUI>

    override fun setUp() {
        setUpRecycler()
        viewModel.getProductList()
        bindObserves()

    }

    override fun listeners() {

    }

    private fun setUpRecycler() {
        productsAdapter = ProductsRecyclerAdapter {
            openProductDetails(it.title, it.price)
        }
        binding.productsRecycler.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.productsRecycler.adapter = productsAdapter

        categoriesAdapter = CategoriesRecyclerAdapter {
            productsAdapter.resetFilter(productsList)
//            productsAdapter.submitList(productsList)
            d("fragmentProductList", productsAdapter.currentList.toString())
            if (it.categoryName != "All"){
                productsAdapter.filterByCategory(it.categoryName)


            }

        }
        binding.categoryRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecycler.adapter = categoriesAdapter

    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productUIListFlow.collect() {
                    when (it) {

                        is Resource.Loading -> {
                            binding.pbProducts.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.pbProducts.visibility = View.GONE
                            productsList = it.responseData
                            productsAdapter.submitList(productsList)
                            categoriesAdapter.submitList(viewModel.getCategories())

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

    private fun openProductDetails(title: String, price: String) {
        findNavController().navigate(
            ProductsFragmentDirections.actionProductsFragmentToProductDetailFragment(
                title,
                price
            )
        )
    }

}