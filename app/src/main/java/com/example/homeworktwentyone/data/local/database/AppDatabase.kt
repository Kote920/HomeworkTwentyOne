package com.example.homeworktwentyone.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homeworktwentyone.data.local.dao.ProductDao
import com.example.homeworktwentyone.data.local.entity.ProductEntity

@Database(entities = [ProductEntity::class],version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}