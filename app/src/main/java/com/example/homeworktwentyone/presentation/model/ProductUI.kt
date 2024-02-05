package com.example.homeworktwentyone.presentation.model

data class ProductUI(
    var id: Int,
    var cover: String,
    var price: String,
    var title: String,
    var favorite: Boolean,
    var category: String
) {
}