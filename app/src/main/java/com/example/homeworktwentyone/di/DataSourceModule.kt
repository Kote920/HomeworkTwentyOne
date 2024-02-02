package com.example.homeworktwentyone.di

import android.content.Context
import com.example.homeworktwentyone.data.dataSource.PreferencesHelper
import com.example.homeworktwentyone.data.dataSource.ProductDataSource
import com.example.homeworktwentyone.data.dataSource.ProductLocalDataSource
import com.example.homeworktwentyone.data.dataSource.ProductRemoteDataSource
import com.example.homeworktwentyone.data.local.dao.ProductDao
import com.example.homeworktwentyone.data.remote.service.ClothesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  DataSourceModule {

    @Singleton
    @Provides
    fun provideProductLocalDataSource(productDao: ProductDao, preferencesHelper: PreferencesHelper): ProductDataSource.Local {
        return ProductLocalDataSource(productDao,preferencesHelper )


    }

    @Singleton
    @Provides
    fun provideProductRemoteDataSource(clothesService: ClothesService): ProductDataSource.Remote {
        return ProductRemoteDataSource(clothesService)


    }

    @Singleton
    @Provides
    fun providePreferencesHelper(@ApplicationContext context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }

}