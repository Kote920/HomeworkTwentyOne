package com.example.homeworktwentyone.data.dataSource

import com.example.homeworktwentyone.data.common.Resource
import com.example.homeworktwentyone.data.local.dao.ProductDao
import com.example.homeworktwentyone.data.model.Product
import com.example.homeworktwentyone.data.remote.mapper.toExternalModel
import com.example.homeworktwentyone.data.remote.model.ProductResponseDto
import com.example.homeworktwentyone.data.remote.service.ClothesService
import com.example.homeworktwentyone.domain.model.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(private val clothesService: ClothesService) :
    ProductDataSource.Remote {
    override suspend fun getProducts(): Resource<List<Product>> = withContext(Dispatchers.IO) {
        try {

            val response = clothesService.getClothes()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!.map {
                    it.toExternalModel()
                })
            } else {
                Resource.Failed("")
            }

        } catch (e: Exception) {
            Resource.Failed("")
        }

    }
}