package com.example.homeworktwentyone.data.remote.service

import com.example.homeworktwentyone.data.remote.model.ProductResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ClothesService {

    @GET("1775d634-92dc-4c32-ae71-1707b8cfee41")
    suspend fun getClothes(): Response<List<ProductResponseDto>>


}