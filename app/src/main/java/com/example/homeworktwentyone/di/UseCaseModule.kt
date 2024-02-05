package com.example.homeworktwentyone.di

import com.example.homeworktwentyone.domain.repository.GetClothesRepository
import com.example.homeworktwentyone.domain.useCase.GetProductsUseCase
import com.example.homeworktwentyone.domain.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetProductsUseCase(getProductsRepository: GetClothesRepository, networkUtils: NetworkUtils): GetProductsUseCase {
        return GetProductsUseCase(getProductsRepository, networkUtils)
    }
}