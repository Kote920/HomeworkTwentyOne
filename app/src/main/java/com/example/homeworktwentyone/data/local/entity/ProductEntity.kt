package com.example.homeworktwentyone.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "cover") val cover: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean
)


{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}

