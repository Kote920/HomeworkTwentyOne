package com.example.homeworktwentyone.data.repository

import com.example.homeworktwentyone.data.common.ConnectivityUtils
import com.example.homeworktwentyone.data.common.Resource
import com.example.homeworktwentyone.data.dataSource.ProductLocalDataSource
import com.example.homeworktwentyone.data.dataSource.ProductRemoteDataSource
import com.example.homeworktwentyone.data.mapper.toDomain
import com.example.homeworktwentyone.domain.model.ProductResponse
import com.example.homeworktwentyone.domain.repository.GetClothesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPoductsRepository @Inject constructor(
    private val productLocalDataSource: ProductLocalDataSource,
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val connectivityUtils: ConnectivityUtils
) : GetClothesRepository {
//    override suspend fun getProducts(): Flow<Resource<List<ProductResponse>>> =
//        withContext(Dispatchers.IO) {
//
//            flow {
//                emit(Resource.Loading())
//
//                if(connectivityUtils.isNetworkAvailable()){
//                    emit(Resource.Success(getProductsFromRemote()))
//                }else{
//                    emit(Resource.Success(getProductsFromLocal()))
//                }
//            }
//
//        }


    override suspend fun getProductsFromLocal(): Resource<List<ProductResponse>> =
        withContext(Dispatchers.IO) {

            val result = productLocalDataSource.getProducts()
            if (result.isEmpty()) {
                Resource.Failed("")
            } else {
                Resource.Success(result.map {
                    it.toDomain()
                })
            }

        }

    override suspend fun getProductsFromRemote(): Resource<List<ProductResponse>> =
        withContext(Dispatchers.IO) {

            when (val result = productRemoteDataSource.getProducts()) {
                is Resource.Success -> {
                    productLocalDataSource.insertProducts(result.responseData.map {
                        it.toDomain()
                    })
                    Resource.Success(result.responseData.map {
                        it.toDomain()
                    })
                }

                is Resource.Failed -> Resource.Failed(result.message)
                else -> {
                    Resource.Loading()
                }
            }
        }


}


