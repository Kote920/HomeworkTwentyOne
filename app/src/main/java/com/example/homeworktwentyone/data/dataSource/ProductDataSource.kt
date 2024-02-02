package com.example.homeworktwentyone.data.dataSource

import com.example.homeworktwentyone.data.common.Resource
import com.example.homeworktwentyone.data.local.entity.ProductEntity
import com.example.homeworktwentyone.data.model.Product
import com.example.homeworktwentyone.data.remote.model.ProductResponseDto
import com.example.homeworktwentyone.domain.model.ProductResponse
import retrofit2.Response

interface ProductDataSource {

    interface Remote{
        suspend fun getProducts(): Resource<List<Product>>
    }

    interface Local{
        suspend fun getProducts(): List<Product>
        suspend fun insertProducts(products: List<ProductResponse>): List<Product>

    }

}