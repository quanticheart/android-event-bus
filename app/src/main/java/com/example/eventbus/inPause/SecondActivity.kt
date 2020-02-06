package com.example.eventbus.inPause

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eventbus.R
import com.example.eventbus.entity.MessageEvent
import com.example.eventbus.entity.MessageEvent2
import kotlinx.android.synthetic.main.activity_second.*
import org.greenrobot.eventbus.EventBus

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btnMsg.setOnClickListener {
            EventBus.getDefault().post(MessageEvent("Hello everyone!"))
        }

        btnMsg2.setOnClickListener {
            EventBus.getDefault().post(MessageEvent2("Hello everyone 2!"))
        }
    }
}
