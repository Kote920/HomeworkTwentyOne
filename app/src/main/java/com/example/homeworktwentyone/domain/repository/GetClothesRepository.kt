package com.example.homeworktwentyone.domain.repository

import com.example.homeworktwentyone.data.common.Resource
import com.example.homeworktwentyone.domain.model.ProductResponse
import kotlinx.coroutines.flow.Flow

interface GetClothesRepository {

//    suspend fun getProducts(): Flow<Resource<List<ProductResponse>>>

    suspend fun getProductsFromLocal(): Resource<List<ProductResponse>>
    suspend fun getProductsFromRemote(): Resource<List<ProductResponse>>

//    suspend fun getById(id: Int): Flow<Resource<ProductResponse>>

}