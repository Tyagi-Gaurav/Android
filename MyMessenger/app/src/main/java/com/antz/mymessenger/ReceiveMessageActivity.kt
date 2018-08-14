package com.antz.mymessenger

import android.app.Activity
import android.content.Intent

import android.os.Bundle
import android.widget.TextView

class ReceiveMessageActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive_message)

        val findViewById = findViewById<TextView>(R.id.messageWrite)
        val stringExtra = intent.getStringExtra(Intent.EXTRA_TEXT)
        findViewById.setText(stringExtra)
    }
}




