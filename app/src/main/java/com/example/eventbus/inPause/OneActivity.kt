package com.example.eventbus.inPause

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eventbus.R
import com.example.eventbus.entity.MessageEvent
import com.example.eventbus.entity.MessageEvent2
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class OneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDefault.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    // This method will be called when a MessageEvent is posted (in the UI thread for Toast)
    @Subscribe
    fun onMessageEvent(event: MessageEvent) {
        Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
    }

    @Subscribe
    fun onMessageEvent(event: MessageEvent2) {
        Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
    }

    public override fun onStart() {
        EventBus.getDefault().register(this)
        super.onStart()
    }

    /**
     * Set OnDestroy, not OnStop, for receive events from others activitys
     */
    public override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
