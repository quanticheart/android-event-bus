package com.example.eventbus

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eventbus.entity.MessageEvent
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {

    /**
     * https://gunhansancar.com/ease-communication-between-activities-fragments-services/
     * https://stackoverflow.com/questions/43250510/how-to-receive-eventbus-events-when-activity-is-in-the-background
     * https://androidclarified.com/eventbus-android-example-full-tutorial/
     * https://code.tutsplus.com/pt/tutorials/quick-tip-how-to-use-the-eventbus-library--cms-22694
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDefault.setOnClickListener {
            EventBus.getDefault().post(MessageEvent("Hello everyone!"))
        }
    }

    @Subscribe
    fun onMessageEvent(event: MessageEvent) {
        Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
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
