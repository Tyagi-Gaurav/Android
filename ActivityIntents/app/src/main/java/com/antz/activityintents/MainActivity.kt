package com.antz.activityintents

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {

    private val LOG_TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun launchSecondActivity(view : View) {
        Log.d(LOG_TAG, "Button Clicked!")
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}
