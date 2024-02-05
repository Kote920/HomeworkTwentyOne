package com.example.homeworktwentyone.presentation.feature

// ChargingChangedReceiver.kt
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ChargingChangedReceiver(private val callback: ChargingChangedCallback) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_POWER_CONNECTED) {
            callback.onChargingChanged(true)
        } else if (intent?.action == Intent.ACTION_POWER_DISCONNECTED) {
            callback.onChargingChanged(false)
        }
    }
}

interface ChargingChangedCallback {
    fun onChargingChanged(isCharging: Boolean)
}
