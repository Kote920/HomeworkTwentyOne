package com.example.homeworktwentyone.domain.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

class NetworkUtils {
    suspend fun isNetworkAvailable(): Boolean = withContext(Dispatchers.IO) {
        try {
            val socket = Socket()
            socket.connect(InetSocketAddress("www.google.com", 80), 1500)
            socket.close()
            true
        } catch (e: IOException) {
            false
        }
    }
}
