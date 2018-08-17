package com.antz.stopwatch

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import java.util.*

class MainActivity : Activity() {

    var seconds = 0
    var running = false
    var wasRunning = false
    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seconds = savedInstanceState?.getInt("seconds") ?: 0
        running = savedInstanceState?.getBoolean("running") ?: false
        runTimer()
    }

    override fun onStart() {
        super.onStart()
        running = wasRunning
    }

    override fun onStop() {
        super.onStop()
        wasRunning = running
        running = false
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt("seconds", seconds)
        outState?.putBoolean("running", running)
        outState?.putBoolean("wasRunning", wasRunning)
    }

    fun onClickStart(view: View) {
        running = true
    }

    fun onClickStop(view: View) {
        running = false
    }

    fun onClickReset(view: View) {
        running = false
        seconds = 0
    }

    fun runTimer() {
        handler.post(monitor)
    }

    val monitor = object : Runnable {
        override fun run() {
            handler.post {
                val timeView = findViewById<TextView>(R.id.timeView)

                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val sec = seconds % 60

                val time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, sec)
                timeView.setText(time)

                if (running) {
                    seconds++
                }
            }
            handler.postDelayed(this, 1000)
        }
    }
}

