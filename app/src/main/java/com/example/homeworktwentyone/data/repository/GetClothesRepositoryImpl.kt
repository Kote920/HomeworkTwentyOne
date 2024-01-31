package com.example.homeworktwentyone.data.repository

import android.util.Log.d
import com.example.homeworktwentyone.data.common.ConnectivityUtils
import com.example.homeworktwentyone.data.common.Resource
import com.example.homeworktwentyone.data.local.dao.ProductDao
import com.example.homeworktwentyone.data.local.mapper.toEntity
import com.example.homeworktwentyone.data.local.mapper.toRemote
import com.example.homeworktwentyone.data.remote.mapper.toDomain
import com.example.homeworktwentyone.data.remote.service.ClothesService
import com.example.homeworktwentyone.domain.model.ProductResponse
import com.example.homeworktwentyone.domain.repository.GetClothesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetClothesRepositoryImpl @Inject constructor(
    private val clothesService: ClothesService,
    private val connectivityUtils: ConnectivityUtils,
    private val productDao: ProductDao,
) : GetClothesRepository {

    override suspend fun getClothesList(): Flow<Resource<List<ProductResponse>>> {
        return flow {
            try {
                emit(Resource.Loading())

                if (connectivityUtils.isNetworkAvailable()) {
                    val response = clothesService.getClothes()

                    if (response.isSuccessful) {
                        val clothesList = response.body()!!.map {
                            it.toDomain()
                        }
                        for (each in clothesList) {
                            insertProduct(each)
                        }
                        emit(Resource.Success(clothesList))

                    } else {
                        emit(Resource.Failed("Request failed"))
                    }
                } else {

                    emit(Resource.Success(getALl()))

                }
            } catch (e: Exception) {
                emit(Resource.Failed("Request failed"))
            }
        }
    }

    override suspend fun insertProduct(product: ProductResponse) {
        productDao.insertProduct(product = product.toEntity())
    }

    override suspend fun getALl(): List<ProductResponse> {
        return productDao.getAllProducts().map {
            it.toRemote().toDomain()

        }
    }

    override suspend fun getById(id: Int): Flow<Resource<ProductResponse>> {

        return flow {
            emit(Resource.Loading())
            val prod = productDao.getProductById(id)
            if(prod != null){
                emit(Resource.Success(prod.toRemote().toDomain()))
            }else{
                emit(Resource.Failed("Not in database"))
                getClothesList()
            }
        }
    }

}



