package com.antz.joke

import android.app.IntentService
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.app.NotificationChannel
import android.os.Build


private const val SERVICE_NAME = "DelayedMessageService"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
class DelayedMessageService : IntentService(SERVICE_NAME) {

    companion object {
        const val EXTRA_MESSAGE = "message"
        const val NOTIFICATION_ID = 5453
    }

    override fun onHandleIntent(intent: Intent?) {
        synchronized(this) {
            try {
                Thread.sleep(10000)
            } catch(e : InterruptedException) {
                e.printStackTrace()
            }
        }

        val stringExtra = intent?.getStringExtra(EXTRA_MESSAGE)
        showText(stringExtra)
    }

    private fun showText(message: String?) {
        Log.v(SERVICE_NAME, "Starting to send notification")
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("default",
                    "Channel name", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Channel description"
            notificationManager.createNotificationChannel(channel)
        } else {
            "default"
        }
        val notificationCompatBuilder = NotificationCompat.Builder(this, "default")
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle(getString(R.string.question))
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(longArrayOf(0, 1000))
                .setAutoCancel(true)

        val actionIntent = Intent(this, MainActivity::class.java)
        val pendingActivity = PendingIntent.getActivity(this, 0, actionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)

        notificationCompatBuilder.setContentIntent(pendingActivity)

        notificationManager.notify(NOTIFICATION_ID, notificationCompatBuilder.build())
        Log.v(SERVICE_NAME, "Notification Sent")
    }
}
