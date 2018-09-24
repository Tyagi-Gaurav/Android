package com.antz.odometer

import android.app.Activity
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.support.v4.app.ActivityCompat
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
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

        if (ContextCompat.checkSelfPermission(this, OdometerService.PERMISSION_STRING) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(OdometerService.PERMISSION_STRING),
                    PERMISSION_REQUEST_CODE
            )
        } else {
            val intent = Intent(this, OdometerService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    private val NOTIFICATION_ID: Int = 423

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>?,
                                            grantResults: IntArray?) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                    if (grantResults!!.isNotEmpty() &&
                            grantResults[0] == PERMISSION_REQUEST_CODE) {
                        val intent = Intent(this, OdometerService::class.java)
                        bindService(intent, connection, Context.BIND_AUTO_CREATE)
                    } else {
                        val builder = NotificationCompat.Builder(this, "DEFAULT")
                                .setSmallIcon(android.R.drawable.ic_menu_compass)
                                .setContentTitle(resources.getString(R.string.app_name))
                                .setContentText(resources.getString(R.string.permission_denied))
                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                .setVibrate(longArrayOf(1000, 1000))
                                .setAutoCancel(true)
                        val actionIntent = Intent(this, MainActivity::class.java)
                        val actionPendingIntent = PendingIntent.getActivity(this,
                                0,
                                actionIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT)
                        builder.setContentIntent(actionPendingIntent)

                        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                                as NotificationManager
                        notificationManager.notify(NOTIFICATION_ID, builder.build())

                    }

            }
        }

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

    companion object {
        const val PERMISSION_REQUEST_CODE = 689
    }

}
