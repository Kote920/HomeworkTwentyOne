package com.example.homeworktwentyone.di

import com.example.homeworktwentyone.domain.repository.GetClothesRepository
import com.example.homeworktwentyone.domain.useCase.GetClothesUseCase
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
    fun provideGetClothesUseCase(getClothesRepository: GetClothesRepository): GetClothesUseCase {
        return GetClothesUseCase(getClothesRepository)
    }
}