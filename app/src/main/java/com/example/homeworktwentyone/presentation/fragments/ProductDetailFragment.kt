package com.example.homeworktwentyone.presentation.fragments

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.homeworktwentyone.R
import com.example.homeworktwentyone.databinding.FragmentProductDetailBinding
import com.example.homeworktwentyone.presentation.base.BaseFragment
import com.example.homeworktwentyone.presentation.feature.AudioPlaybackService
import com.example.homeworktwentyone.presentation.feature.ChargingChangedCallback
import com.example.homeworktwentyone.presentation.feature.ChargingChangedReceiver

class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>(FragmentProductDetailBinding::inflate), ChargingChangedCallback {

    private val navArgs: ProductDetailFragmentArgs by navArgs()
    private val chargingChangedReceiver = ChargingChangedReceiver(this)
    override fun setUp() {
       binding.tvTitle.text = navArgs.title
        binding.tvPrice.text = navArgs.price

    }

    override fun listeners() {

    }

    override fun onChargingChanged(isCharging: Boolean) {

        if (isCharging){
            Toast.makeText(requireContext(), "Charging!!", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), "Unplugged!!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        val chargingFilter = IntentFilter()
        chargingFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        chargingFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        requireActivity().registerReceiver(chargingChangedReceiver, chargingFilter)
    }

    override fun onStop() {
        super.onStop()
        requireActivity().unregisterReceiver(chargingChangedReceiver)
    }

}