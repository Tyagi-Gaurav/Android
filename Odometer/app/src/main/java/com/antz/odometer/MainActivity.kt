package com.antz.odometer

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.widget.TextView
import java.util.*

class MainActivity : Activity() {

    private var odometerService : OdometerService? = null
    private var bound: Boolean = false
    val handler = Handler()

    val connection : ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            bound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val odometerBinder = service as OdometerService.OdometerBinder
            odometerService = odometerBinder.getOdometer()
            bound = true
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, OdometerService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayDistance()
    }

    override fun onStop() {
        super.onStop()
        if (bound) {
            unbindService(connection)
            bound = false
        }
    }

    private fun displayDistance() {
        handler.post(monitor)
    }

    val monitor : Runnable = object : Runnable {
        override fun run() {
            handler.post{
                val distanceView = findViewById<TextView>(R.id.distance)
                var distance = 0.0
                if (bound && odometerService != null) {
                    distance = odometerService!!.getDistance()
                }

                val distanceString = String.format(Locale.getDefault(), "%1$,.2f miles", distance)
                distanceView.text = distanceString
            }

            handler.postDelayed(this, 1000)
        }
    }

}
