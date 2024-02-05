package com.example.homeworktwentyone.presentation.adapters

import android.graphics.Color
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworktwentyone.R
import com.example.homeworktwentyone.databinding.CategoryItemRecyclerBinding
import com.example.homeworktwentyone.databinding.ItemRecyclerviewBinding
import com.example.homeworktwentyone.presentation.extension.loadImage
import com.example.homeworktwentyone.presentation.model.Category
import com.example.homeworktwentyone.presentation.model.ProductUI


class CategoriesRecyclerAdapter(private val onItemClick: (Category) -> Unit) :
    ListAdapter<Category, CategoriesRecyclerAdapter.CategoriesViewHolder>(CategoriesDiffUtil()) {

    private lateinit var newList: List<Category>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoriesViewHolder(
        CategoryItemRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind()
    }

    inner class CategoriesViewHolder(private val binding: CategoryItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: Category

        fun bind() {

            model = currentList[adapterPosition]

            binding.apply {
                tvCategory.text = model.categoryName
                if (model.isSelected) {
                    root.setBackgroundResource(R.drawable.bg_green)
                    tvCategory.setTextColor(Color.WHITE)
                } else {
                    root.setBackgroundResource(R.drawable.bg_grey)
                    tvCategory.setTextColor(Color.GRAY)
                }
            }
            listeners()
        }

        private fun listeners() {
            binding.root.setOnClickListener {
                onItemClick.invoke(model)
                setAllOff()
                for(each in newList){
                    if( each.categoryName == model.categoryName){
                        each.isSelected = true
                    }
                }
                submitList(newList)
            }
        }

    }

    class CategoriesDiffUtil : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
//            d("areContentTheSameCheck", (oldItem == newItem).toString())
            return oldItem.categoryName == newItem.categoryName
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

    fun setAllOff() {
        newList = currentList.map { it.copy(isSelected = false) }

    }


}
