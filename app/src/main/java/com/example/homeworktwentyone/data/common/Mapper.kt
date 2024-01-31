package com.example.homeworktwentyone.data.common

interface Mapper<F,T> {

    fun mapFrom(from:F):T

}