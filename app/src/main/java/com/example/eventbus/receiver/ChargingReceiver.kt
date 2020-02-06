package com.example.eventbus.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.format.Time

import com.example.eventbus.entity.ChargingEvent
import com.example.eventbus.entity.TimeData

import org.greenrobot.eventbus.EventBus

import java.util.Calendar

class ChargingReceiver : BroadcastReceiver() {

    private val bus = EventBus.getDefault()

    override fun onReceive(context: Context, intent: Intent) {
        var event: ChargingEvent? = null

        // Get current time
        val now = Time()
        now.setToNow()
        val timeOfEvent = now.format("%H:%M:%S")

        val eventData = "@$timeOfEvent this device started "
        if (intent.action == Intent.ACTION_POWER_CONNECTED) {
            event = ChargingEvent(eventData + "charging.")
        } else if (intent.action == Intent.ACTION_POWER_DISCONNECTED) {
            event = ChargingEvent(eventData + "discharging.")
        }

        if (intent.action!!.compareTo(Intent.ACTION_TIME_TICK) == 0) {
            bus.post(
                TimeData(
                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY).toLong(),
                    Calendar.getInstance().get(Calendar.MINUTE).toLong()
                )
            )
        }
        // Post the event
        bus.post(event)
    }

}
