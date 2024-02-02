package com.example.homeworktwentyone.data.repository

import com.example.homeworktwentyone.data.common.Resource
import com.example.homeworktwentyone.data.dataSource.ProductLocalDataSource
import com.example.homeworktwentyone.data.dataSource.ProductRemoteDataSource
import com.example.homeworktwentyone.data.mapper.toDomain
import com.example.homeworktwentyone.domain.model.ProductResponse
import com.example.homeworktwentyone.domain.repository.GetClothesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetClothesRepositoryImpl @Inject constructor(
    private val productLocalDataSource: ProductLocalDataSource,
    private val productRemoteDataSource: ProductRemoteDataSource
) : GetClothesRepository {
    override suspend fun getProducts(): Flow<Resource<List<ProductResponse>>> =
        withContext(Dispatchers.IO) {

            flow {
                emit(Resource.Loading())
                val productLocalList = getProductsFromLocal()
                if (productLocalList.isNotEmpty()) {
                    if (productLocalDataSource.isExpired()) {
                        emit(Resource.Success(getProductsFromRemote()))
                    } else {
                        emit(Resource.Success(productLocalList))
                    }
                } else {
                    emit(Resource.Success(getProductsFromRemote()))
                }

            }

        }


    override suspend fun getProductsFromLocal(): List<ProductResponse> =
        withContext(Dispatchers.IO) {
            productLocalDataSource.getProducts().map {
                it.toDomain()
            }

        }

    override suspend fun getProductsFromRemote(): List<ProductResponse> =
        withContext(Dispatchers.IO) {
            when (val result = productRemoteDataSource.getProducts()) {
                is Resource.Success -> {
                    val productRemoteList = result.responseData
                    if (productRemoteList.isNotEmpty()) {
                        productLocalDataSource.insertProducts(productRemoteList.map {
                            it.toDomain()
                        })
                        result.responseData.map {
                            it.toDomain()
                        }
                    } else {
                        getProductsFromLocal()
                    }
                }

                else -> {
                    listOf()
                }
            }

        }

}


