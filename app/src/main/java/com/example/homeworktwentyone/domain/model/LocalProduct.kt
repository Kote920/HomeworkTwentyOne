package com.example.homeworktwentyone.domain.model

import android.graphics.drawable.Drawable

data class LocalProduct(
    val id: Int,
    val cover: Drawable?,
    val price: String,
    val title: String,
    val favorite: Boolean
)