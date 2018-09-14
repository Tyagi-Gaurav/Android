package com.antz.joke

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view : View) {
        val intent = Intent(this, DelayedMessageService::class.java)
        intent.putExtra(DelayedMessageService.EXTRA_MESSAGE, resources.getString(R.string.response))
        startService(intent)
    }
}
