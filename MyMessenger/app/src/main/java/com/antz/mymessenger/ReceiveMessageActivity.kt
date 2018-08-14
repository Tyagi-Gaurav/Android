package com.antz.mymessenger

import android.app.Activity

import android.os.Bundle
import android.widget.TextView

class ReceiveMessageActivity : Activity() {

    companion object {
        const val EXTRA_MESSAGE = "message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive_message)

        val findViewById = findViewById<TextView>(R.id.messageWrite)
        val stringExtra = intent.getStringExtra(EXTRA_MESSAGE)
        findViewById.setText(stringExtra)
    }
}




