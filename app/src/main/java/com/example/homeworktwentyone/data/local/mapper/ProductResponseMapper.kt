package com.example.homeworktwentyone.data.local.mapper

import com.example.homeworktwentyone.data.local.entity.ProductEntity
import com.example.homeworktwentyone.data.remote.model.ProductResponseDto
import com.example.homeworktwentyone.domain.model.ProductResponse
fun ProductResponse.toEntity() = ProductEntity(
    id = id,
    cover = cover,
    price = price,
    title = title,
    favorite = favorite
)

fun ProductEntity.toRemote() = ProductResponseDto(
    id = id,
    cover = cover,
    price = price,
    title = title,
    favorite = favorite
)


