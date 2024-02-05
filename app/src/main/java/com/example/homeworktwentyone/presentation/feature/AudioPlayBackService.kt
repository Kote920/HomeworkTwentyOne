package com.example.homeworktwentyone.presentation.feature

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.homeworktwentyone.R
class AudioPlaybackService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        // Initialize and set up your MediaPlayer here
        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        mediaPlayer.setDataSource(applicationContext, Uri.parse("android.resource://${packageName}/${R.raw.five_o_five}"))
        mediaPlayer.prepare()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Start audio playback
        try {
            mediaPlayer.start()
        } catch (e: IllegalStateException) {
            // Handle any exceptions during playback
            e.printStackTrace()
        }

        // Make this service run in the foreground
        startForeground(NOTIFICATION_ID, createNotification())

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release resources when the service is destroyed
        try {
            mediaPlayer.stop()
            mediaPlayer.release()
        } catch (e: IllegalStateException) {
            // Handle any exceptions during resource release
            e.printStackTrace()
        }
        stopForeground(true)
    }

    private fun createNotification(): Notification {
        // Create a notification for the foreground service
        // You can customize the notification based on your app's requirements
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Audio Playback")
            .setContentText("Playing audio in the background")
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Customize the icon
            .build()
    }

    companion object {
        private const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "AudioPlaybackChannel"
    }
}
