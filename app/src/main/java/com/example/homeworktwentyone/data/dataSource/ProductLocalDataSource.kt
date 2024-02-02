package com.example.homeworktwentyone.data.dataSource

import com.example.homeworktwentyone.data.local.dao.ProductDao
import com.example.homeworktwentyone.data.local.mapper.toEntity
import com.example.homeworktwentyone.data.local.mapper.toExternalModel
import com.example.homeworktwentyone.data.model.Product
import com.example.homeworktwentyone.domain.model.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductLocalDataSource @Inject constructor(
    private val productDao: ProductDao,
    private val preferencesHelper: PreferencesHelper
) : ProductDataSource.Local {

    companion object {
        private const val EXPIRATION_TIME = (60 * 10 * 1000).toLong()
    }


    override suspend fun getProducts(): List<Product> =
        withContext(Dispatchers.IO) {
            productDao.getAllProducts().map { it.toExternalModel() }

        }


    override suspend fun insertProducts(products: List<ProductResponse>): List<Product> =
        withContext(Dispatchers.IO) {
            productDao.deleteAll()
            for (each in products) {
                productDao.insertProduct(each.toEntity())
            }
            setLastCacheTime(System.currentTimeMillis())
            productDao.getAllProducts().map { it.toExternalModel() }
        }


    private fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

    fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }
}