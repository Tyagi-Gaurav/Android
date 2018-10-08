package com.antz.mycalendar

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.action.ViewActions.swipeRight
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CalendarTest {

    @Rule
    @JvmField
    val mainActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun screenLoadShouldShowCurrentMonth() {
        //given
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val monthString = SimpleDateFormat("MMMM").format(calendar.time)
        val year = calendar.get(Calendar.YEAR)

        //then
        onView(withId(R.id.month_title))
                .check(matches(withText(containsString("$monthString, $year"))))
    }

    @Test
    fun onSwipeRightShouldShowPreviousMonth() {
        //given
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.MONTH, -1)

        val monthString = SimpleDateFormat("MMMM").format(calendar.time)
        val year = calendar.get(Calendar.YEAR)

        //when
        onView(withId(R.id.month_title))
                .perform(swipeRight())

        //then
        onView(allOf(withId(R.id.month_title),
                withText("$monthString, $year")))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun onSwipeLeftShouldShowNextMonth() {
        //given
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.MONTH, 1)

        val monthString = SimpleDateFormat("MMMM").format(calendar.time)
        val year = calendar.get(Calendar.YEAR)

        //when
        onView(withId(R.id.month_title))
                .perform(swipeLeft())

        //then
        onView(allOf(withId(R.id.month_title),
                withText("$monthString, $year")))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun onViewShouldDisplayAllDaysOfTheMonth() {
        //given
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val monthString = SimpleDateFormat("MMMM").format(calendar.time)
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)

        //then
        for (i in 1..calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            val id = (((year * 100) + month) * 100) + i
            onView(withId(id))
                    .check(matches(withText(containsString("$i"))))
        }
    }
}