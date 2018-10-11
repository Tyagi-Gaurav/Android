package com.antz.mycalendar

import android.arch.lifecycle.ViewModelProvider
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mGestureDetector: GestureDetectorCompat
    private lateinit var instance: ViewModelProvider.AndroidViewModelFactory
    private lateinit var calendarModel: CalendarModel
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mGestureDetector = GestureDetectorCompat(this, MyGestureListener())
        instance = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
        val viewModelProvider = ViewModelProvider(this, instance)
        calendarModel = viewModelProvider.get(CalendarModel::class.java)
        calendarModel.year = Calendar.getInstance().get(Calendar.YEAR)
        calendarModel.month = Calendar.getInstance().get(Calendar.MONTH)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        Log.d("MainActivity", "Support Action Bar is $supportActionBar")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.sync -> {
                handler.post(calendarSyncer)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
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

    fun openNewMonthFragment(monthFragment: MonthFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.monthFragment, monthFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private val calendarSyncer : Runnable = Runnable {
        handler.post{
            val myCalendarAdapter = MyCalendarAdapter(contentResolver, this)
            myCalendarAdapter.getEvents()
            val makeText = Toast.makeText(this, "Syncing calendar events", Toast.LENGTH_SHORT)
            makeText.show()
        }
    }

    companion object {
        private const val MIN_DISTANCE: Float = 150.0F
    }
}


