package com.antz.mycalendar

import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

class MainActivity : AppCompatActivity() {

    private lateinit var mGestureDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mGestureDetector = GestureDetectorCompat(this, MyGestureListener())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //Log.d("MyMainActivity", "Touch Event Detected $event")
        mGestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    private class MyGestureListener : GestureDetector.SimpleOnGestureListener() {
        private val MIN_DISTANCE: Float = 150.0F

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            //Log.d("MyGestureListener", "onFling: $e1 $e2")
            val delta = e2!!.x - e1!!.x
            if (Math.abs(delta) > MIN_DISTANCE) {
                if (e2.x > e1.x) {
                    Log.d("MyMainActivity", "left2right swipe")
                } else {
                    Log.d("MyMainActivity", "right2left swipe")
                }
            }
            return true
        }
    }
}
