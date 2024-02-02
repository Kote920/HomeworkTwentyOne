package com.example.homeworktwentyone.di

import com.example.homeworktwentyone.data.common.ConnectivityUtils
import com.example.homeworktwentyone.data.dataSource.ProductLocalDataSource
import com.example.homeworktwentyone.data.dataSource.ProductRemoteDataSource
import com.example.homeworktwentyone.data.local.dao.ProductDao
import com.example.homeworktwentyone.data.remote.service.ClothesService
import com.example.homeworktwentyone.data.repository.GetClothesRepositoryImpl
import com.example.homeworktwentyone.domain.repository.GetClothesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGetClothesRepository(
        clothesService: ClothesService,
        connectivityUtils: ConnectivityUtils,
        productDao: ProductDao,
        productLocalDataSource: ProductLocalDataSource,
        productRemoteDataSource: ProductRemoteDataSource

    ): GetClothesRepository {
        return GetClothesRepositoryImpl(
            productLocalDataSource =  productLocalDataSource,
            productRemoteDataSource =  productRemoteDataSource
//            clothesService,
//            connectivityUtils = connectivityUtils,
//            productDao = productDao


        )
    }
}