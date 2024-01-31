package com.example.homeworktwentyone.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktwentyone.R
import com.example.homeworktwentyone.databinding.ItemRecyclerviewBinding
import com.example.homeworktwentyone.presentation.extension.loadImage
import com.example.homeworktwentyone.presentation.model.Product

class ProductsRecyclerAdapter(private val onItemClick: (Product) -> Unit) :
    ListAdapter<Product, ProductsRecyclerAdapter.ProductsViewHolder>(ProductsDiffUtil()) {

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
        private lateinit var model: Product

        fun bind() {
            model = currentList[adapterPosition]
            binding.apply {
                tvDescription.text = model.title
                tvPrice.text = model.price
                if(model.favorite){
                    ibFavorite.setBackgroundResource(R.drawable.bg_circle_green)
                }else{
                    ibFavorite.setBackgroundResource(R.drawable.bg_circle_white)
                }
                ivProduct.loadImage(model.cover)

            }
            listeners()
        }
        private fun listeners(){
            binding.root.setOnClickListener {
                onItemClick.invoke(model)
            }
        }

    }

    class ProductsDiffUtil : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}
