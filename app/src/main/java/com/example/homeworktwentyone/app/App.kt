package com.example.homeworktwentyone.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.example.homeworktwentyone.presentation.feature.AudioPlaybackService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    companion object{
        lateinit var application: Application
        private set
    }


    override fun onCreate() {
        super.onCreate()
        application = this
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                AudioPlaybackService.CHANNEL_ID,
                "Audio Playback Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

}