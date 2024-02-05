package com.example.homeworktwentyone.di

import android.content.Context
import com.example.homeworktwentyone.data.common.ConnectivityUtils
import com.example.homeworktwentyone.domain.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideConnectivityUtils(@ApplicationContext context: Context): ConnectivityUtils {
        return ConnectivityUtils(context)
    }

    @Provides
    @Singleton
    fun provideNetworkUtils(): NetworkUtils {
        return NetworkUtils()
    }

}
