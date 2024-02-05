package com.example.homeworktwentyone.presentation.adapters

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktwentyone.R
import com.example.homeworktwentyone.databinding.ItemRecyclerviewBinding
import com.example.homeworktwentyone.presentation.extension.loadImage
import com.example.homeworktwentyone.presentation.model.ProductUI

class ProductsRecyclerAdapter(private val onItemClick: (ProductUI) -> Unit) :
    ListAdapter<ProductUI, ProductsRecyclerAdapter.ProductsViewHolder>(ProductsDiffUtil()) {
    private var filteredProducts: List<ProductUI> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductsViewHolder(
        ItemRecyclerviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind()
    }

    inner class ProductsViewHolder(private val binding: ItemRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: ProductUI

        fun bind() {
            model = currentList[adapterPosition]
            binding.apply {
                tvDescription.text = model.title
                tvPrice.text = model.price
                tvCategory.text = model.category
                if (model.favorite) {
                    ibFavorite.setBackgroundResource(R.drawable.bg_circle_green)
                } else {
                    ibFavorite.setBackgroundResource(R.drawable.bg_circle_white)
                }
                ivProduct.loadImage(model.cover)

            }
            listeners()
        }

        private fun listeners() {
            binding.root.setOnClickListener {
                onItemClick.invoke(model)
            }
        }

    }

    class ProductsDiffUtil : DiffUtil.ItemCallback<ProductUI>() {
        override fun areItemsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductUI, newItem: ProductUI): Boolean {
            return oldItem == newItem
        }
    }

    fun filterByCategory(category: String) {
        filteredProducts = if (category.isBlank()) {
            currentList.toList()
        } else {
            currentList.filter { it.category.equals(category, ignoreCase = true) }
        }
        submitList(filteredProducts)
    }

    fun resetFilter(list: List<ProductUI>) {
        filteredProducts = currentList.toList()
        submitList(list)
    }
}
