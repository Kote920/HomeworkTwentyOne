package com.example.homeworktwentyone.domain.mapper

import com.example.homeworktwentyone.data.common.Mapper
import com.example.homeworktwentyone.data.remote.model.ProductResponseDto
import com.example.homeworktwentyone.domain.model.ProductResponse

class ProductMapperDtoToModel (): Mapper<ProductResponseDto, ProductResponse> {
    override fun mapFrom(from: ProductResponseDto): ProductResponse {
        return ProductResponse(
            id = from.id,
            cover = from.cover,
            price = from.price,
            title = from.title,
            favorite = from.favorite
        )
    }
}