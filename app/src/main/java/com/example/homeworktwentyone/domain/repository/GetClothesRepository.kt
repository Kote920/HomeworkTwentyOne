package com.example.homeworktwentyone.domain.repository

import com.example.homeworktwentyone.data.common.Resource
import com.example.homeworktwentyone.domain.model.ProductResponse
import kotlinx.coroutines.flow.Flow

interface GetClothesRepository {

    suspend fun getClothesList(): Flow<Resource<List<ProductResponse>>>

    suspend fun insertProduct(product: ProductResponse)

    suspend fun getALl(): List<ProductResponse>

    suspend fun getById(id: Int): Flow<Resource<ProductResponse>>

}