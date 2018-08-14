package com.antz.mymessenger

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.view.ViewGroup



class CreateMessageActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_message)
    }

    fun onSendMessage(view : View) {
        val findViewById = findViewById<EditText>(R.id.messageRead)
        val intent = Intent(this, ReceiveMessageActivity::class.java)

        println ("FindView $findViewById")
        intent.putExtra(
                ReceiveMessageActivity.EXTRA_MESSAGE,
                findViewById.text.toString()
        )

        startActivity(intent)
    }
}
