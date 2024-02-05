package com.example.homeworktwentyone.di

import android.content.Context
import androidx.room.Room
import com.example.homeworktwentyone.data.local.dao.ProductDao
import com.example.homeworktwentyone.data.local.database.AppDatabase
import com.example.homeworktwentyone.data.local.migration.Migration_1_2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "Homework-twenty"
        ).addMigrations(Migration_1_2)
            .build()
    }
    //.addMigrations(Migration_1_2)

    @Singleton
    @Provides
    fun provideProductDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productDao()
    }

}