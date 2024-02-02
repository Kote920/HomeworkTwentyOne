package com.example.homeworktwentyone.data.mapper

import com.example.homeworktwentyone.data.model.Product
import com.example.homeworktwentyone.domain.model.ProductResponse


fun Product.toDomain() = ProductResponse(
    id = id,
    cover = cover,
    price = price,
    title = title,
    favorite = favorite
)