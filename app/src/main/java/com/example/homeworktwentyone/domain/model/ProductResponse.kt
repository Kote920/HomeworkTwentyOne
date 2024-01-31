package com.example.homeworktwentyone.domain.model

data class ProductResponse(
    val id: Int,
    val cover: String,
    val price: String,
    val title: String,
    val favorite: Boolean
)