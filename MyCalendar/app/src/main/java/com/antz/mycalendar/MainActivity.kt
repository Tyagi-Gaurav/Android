package com.antz.mycalendar

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.GestureDetector
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.LinearLayout
import java.io.Serializable
import java.lang.ref.WeakReference
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mGestureDetector: GestureDetectorCompat
    private lateinit var instance: ViewModelProvider.AndroidViewModelFactory
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

        //val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        //setSupportActionBar(toolbar)
        //val layoutParams = toolbar.layoutParams as LinearLayout.LayoutParams
//        Log.d("MyCalendar", "Toolbar: ${layoutParams.leftMargin}")
//        Log.d("MyCalendar", "Toolbar: ${layoutParams.rightMargin}")
//        Log.d("MyCalendar", "Toolbar: ${layoutParams.topMargin}")
//        Log.d("MyCalendar", "Toolbar: ${layoutParams.bottomMargin}")

        //Log.d("MainActivity", "Support Action Bar is $supportActionBar")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.sync_action -> {
                val myCalendarAdapter = MyCalendarAdapter(WeakReference(this),
                        calendarModel.year, calendarModel.month)
                myCalendarAdapter.post { myCalendarAdapter.getEvents() }
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
                    openNewMonthFragment()
                    Log.d("MyMainActivity", "${calendarModel.month}, ${calendarModel.year}")
                } else {
                    Log.d("MyMainActivity", "right2left swipe")
                    calendarModel.incrementMonthAndYear()
                    openNewMonthFragment()
                    Log.d("MyMainActivity", "${calendarModel.month}, ${calendarModel.year}")
                }
            }
            return true
        }
    }

    fun openNewMonthFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        findViewById<LinearLayout>(R.id.monthFragment).removeAllViews()
        transaction.replace(R.id.monthFragment, MonthFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun updateModel(eventModel : List<EventModel>) {
        val transaction = supportFragmentManager.beginTransaction()
        val monthFragment = MonthFragment()
        val bundle = Bundle()
        bundle.putSerializable("eventModel", eventModel as Serializable)
        monthFragment.arguments = bundle
        transaction.replace(R.id.monthFragment, monthFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        private const val MIN_DISTANCE: Float = 150.0F
    }
}


