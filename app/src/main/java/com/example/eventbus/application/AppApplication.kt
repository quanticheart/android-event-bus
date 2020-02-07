package com.example.eventbus.application

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.eventbus.entity.TimeData
import com.example.eventbus.receiver.ChargingReceiver
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        bus.register(this)

        ChargingReceiver()
    }

    companion object {
        val bus = EventBus.getDefault()!!
        lateinit var appContext: Context
    }

    @Subscribe
    fun receiveClockTime(event: TimeData) {
        Log.w("Time Receiver", event.hour.toString() + ":" + event.minute)
    }
}