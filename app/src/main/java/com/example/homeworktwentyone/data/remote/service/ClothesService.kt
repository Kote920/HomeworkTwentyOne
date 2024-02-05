package com.example.homeworktwentyone.data.remote.service

import com.example.homeworktwentyone.data.remote.model.ProductResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ClothesService {

    @GET("df8d4951-2757-45aa-8f60-bf1592a090ce")
    suspend fun getClothes(): Response<List<ProductResponseDto>>


}