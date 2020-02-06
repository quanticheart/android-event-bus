package com.example.eventbus.broadcast

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.eventbus.entity.ChargingEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class BroadcastActivity : AppCompatActivity() {

    private val bus = EventBus.getDefault()

    private var view: TextView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        view = TextView(this)

        view!!.textSize = 20f
        view!!.setPadding(20, 20, 20, 20)
        view!!.text = "Waiting for events..."

        setContentView(view)

        // Register as a subscriber
        bus.register(this)
    }

    override fun onDestroy() {
        // Unregister
        bus.unregister(this)
        super.onDestroy()
    }

    @SuppressLint("SetTextI18n")
    @Subscribe
    fun onEvent(event: ChargingEvent) {
        view!!.text = view!!.text.toString() + "\n" + event.changeStatus
    }

    public override fun onStart() {
        EventBus.getDefault().register(this)
        super.onStart()
    }

    public override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }

}
