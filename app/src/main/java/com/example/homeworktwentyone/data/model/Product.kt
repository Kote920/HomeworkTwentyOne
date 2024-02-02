package com.example.homeworktwentyone.data.model

import com.squareup.moshi.Json

data class Product(
    val id: Int,
    val cover: String,
    val price: String,
    val title: String,
    val favorite: Boolean
)
