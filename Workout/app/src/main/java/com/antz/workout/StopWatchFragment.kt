package com.antz.workout

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class StopWatchFragment : Fragment(), View.OnClickListener {
    override fun onClick(p0: View?) {
        p0?.let {
            when (p0.id) {
                R.id.startButton -> onClickStart()
                R.id.stopButton -> onClickStop()
                R.id.resButton -> onClickReset()
            }
        }
    }

    var seconds = 0
    var running = false
    var wasRunning = false
    val handler = Handler()

    fun attachOnClickListener(view : View) = view.setOnClickListener(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_stop_watch, container, false)
        runTimer(layout)

        attachOnClickListener(layout.findViewById<Button>(R.id.startButton))
        attachOnClickListener(layout.findViewById<Button>(R.id.stopButton))
        attachOnClickListener(layout.findViewById<Button>(R.id.resButton))

        return layout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        seconds = savedInstanceState?.getInt("seconds") ?: 0
        running = savedInstanceState?.getBoolean("running") ?: false
    }

    private fun onClickStart() {
        running = true
    }

    private fun onClickStop() {
        running = false
    }

    private fun onClickReset() {
        running = false
        seconds = 0
    }

    fun runTimer(view : View) {
        val monitor = object : Runnable {
            override fun run() {
                handler.post {
                    val timeView = view.findViewById<TextView>(R.id.timeView)

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
        handler.post(monitor)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("seconds", seconds)
        outState.putBoolean("running", running)
        outState.putBoolean("wasRunning", wasRunning)
    }


}
