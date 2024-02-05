package com.example.homeworktwentyone.domain.useCase

import com.example.homeworktwentyone.data.common.Resource
import com.example.homeworktwentyone.domain.model.ProductResponse
import com.example.homeworktwentyone.domain.repository.GetClothesRepository
import com.example.homeworktwentyone.domain.utils.NetworkUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: GetClothesRepository,
    private val networkUtils: NetworkUtils
) {
    suspend operator fun invoke(): Flow<Resource<List<ProductResponse>>> {
        return flow {
            emit(Resource.Loading())
            if (networkUtils.isNetworkAvailable()){
                val remoteResponse = repository.getProductsFromRemote()
                emit(remoteResponse)
            }
            else{
                emit(repository.getProductsFromLocal())
            }
        }


    }


}