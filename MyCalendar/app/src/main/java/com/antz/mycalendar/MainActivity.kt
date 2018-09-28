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
    private lateinit var instance : ViewModelProvider.AndroidViewModelFactory
    private lateinit var calendarModel: CalendarModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mGestureDetector = GestureDetectorCompat(this, MyGestureListener())
        instance = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
        val viewModelProvider = ViewModelProvider(this, instance)
        calendarModel = viewModelProvider.get(CalendarModel::class.java)
        calendarModel.year = Calendar.getInstance().get(Calendar.YEAR)
        calendarModel.month = Calendar.getInstance().get(Calendar.MONTH)
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
                    calendarModel.decrementMonthAndYear()
                    openNewMonthFragment(MonthFragment())
                    Log.d("MyMainActivity", "${calendarModel.month}, ${calendarModel.year}")
                } else {
                    Log.d("MyMainActivity", "right2left swipe")
                    calendarModel.incrementMonthAndYear()
                    openNewMonthFragment(MonthFragment())
                    Log.d("MyMainActivity", "${calendarModel.month}, ${calendarModel.year}")
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
