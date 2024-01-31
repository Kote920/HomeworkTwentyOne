package com.example.homeworktwentyone.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.homeworktwentyone.R
import com.example.homeworktwentyone.databinding.FragmentProductDetailBinding
import com.example.homeworktwentyone.presentation.base.BaseFragment

class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>(FragmentProductDetailBinding::inflate) {

    private val navArgs: ProductDetailFragmentArgs by navArgs()
    override fun setUp() {
       binding.tvTitle.text = navArgs.title
        binding.tvPrice.text = navArgs.price

    }

    override fun listeners() {

    }

}