package com.example.homeworktwentyone.data.remote.mapper

import com.example.homeworktwentyone.data.model.Product
import com.example.homeworktwentyone.data.remote.model.ProductResponseDto
import com.example.homeworktwentyone.domain.model.ProductResponse

fun ProductResponseDto.toExternalModel() = Product(
    id = id,
    cover = cover,
    price = price,
    title = title,
    favorite = favorite,
    category = category

)