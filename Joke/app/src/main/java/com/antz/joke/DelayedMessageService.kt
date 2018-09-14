package com.antz.joke

import android.app.IntentService
import android.content.Intent
import android.util.Log


private const val SERVICE_NAME = "DelayedMessageService"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
class DelayedMessageService : IntentService(SERVICE_NAME) {

    companion object {
        const val EXTRA_MESSAGE = "message"
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
        Log.v(SERVICE_NAME, message)
    }
}
