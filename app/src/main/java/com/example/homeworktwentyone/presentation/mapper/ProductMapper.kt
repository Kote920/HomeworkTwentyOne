package com.example.homeworktwentyone.presentation.mapper

import com.example.homeworktwentyone.domain.model.ProductResponse
import com.example.homeworktwentyone.presentation.model.ProductUI


fun ProductResponse.toPresentation() = ProductUI(
    id = id,
    cover = cover,
    price = price,
    title = title,
    favorite = favorite,
    category = category

)

