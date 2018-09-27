package com.antz.mycalendar

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mGestureDetector: GestureDetectorCompat
    private var month = Calendar.getInstance().get(Calendar.MONTH)
    private var year = Calendar.getInstance().get(Calendar.YEAR)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mGestureDetector = GestureDetectorCompat(this, MyGestureListener())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mGestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            val delta = e2!!.x - e1!!.x
            if (Math.abs(delta) > MIN_DISTANCE) {
                if (e2.x > e1.x) {
                    Log.d("MyMainActivity", "left2right swipe")
                    val monthFragment = MonthFragment()
                    monthFragment.year = year
                    monthFragment.month = month
                    monthFragment.decrementMonthAndYear()
                    year = monthFragment.year
                    month = monthFragment.month
                    openNewMonthFragment(monthFragment)
                    Log.d("MyMainActivity", "${monthFragment.month}, ${monthFragment.year}")
                } else {
                    Log.d("MyMainActivity", "right2left swipe")
                    val monthFragment = MonthFragment()
                    monthFragment.year = year
                    monthFragment.month = month
                    monthFragment.incrementMonthAndYear()
                    year = monthFragment.year
                    month = monthFragment.month
                    openNewMonthFragment(monthFragment)
                    Log.d("MyMainActivity", "${monthFragment.month}, ${monthFragment.year}")
                }
            }
            return true
        }
    }

    fun openNewMonthFragment(monthFragment : MonthFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.monthFragment, monthFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        private const val MIN_DISTANCE: Float = 150.0F
    }
}
