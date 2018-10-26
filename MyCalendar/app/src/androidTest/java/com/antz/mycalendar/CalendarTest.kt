package com.antz.mycalendar

import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.provider.CalendarContract
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNot
import org.hamcrest.core.IsNull
import org.junit.Ignore
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
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)

        //then
        for (i in 1..calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            val id = (((year * 100) + month) * 100) + i
            onView(withId(id))
                    .check(matches(withText(containsString("$i"))))
        }
    }

    @Test
    fun shouldHavePermissionsToViewCalendar() {
        //given
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext

        //when
        val packageInfo = targetContext.packageManager.getPackageInfo("com.antz.mycalendar", PackageManager.GET_PERMISSIONS)

        //then
        assertThat(packageInfo.requestedPermissions, IsNot(IsNull()))
        assertThat(packageInfo.requestedPermissions, IsEqual(arrayOf("android.permission.READ_CALENDAR")))
    }

    @Ignore
    fun syncShouldGetEventsAndChangeColorOfDaysWithEventsToBlue() {
        //given
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        val contentResolver = InstrumentationRegistry.getInstrumentation().context.contentResolver
        val uri: Uri = CalendarContract.Events.CONTENT_URI
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val id = (((year * 100) + month) * 100) + calendar.get(Calendar.DATE)

        val packageInfo = targetContext.packageManager.getPackageInfo("com.antz.mycalendar", PackageManager.GET_PERMISSIONS)
        //packageInfo.requestedPermissions

        val contentValues = ContentValues()
        contentValues.put("dtstart", System.currentTimeMillis()/1000)
        contentValues.put("calendar_id", 1)
        contentValues.put("account_name", "chonku@gmail.com")
        contentValues.put("account_type", "com.google")
        contentValues.put("owner_account", "chonku@gmail.com")

            contentResolver?.insert(uri, contentValues)

        //then
        onView(withId(R.id.sync_action))
                .perform(click())
                .check(matches(withText(containsString("$id"))))
                .check(matches(hasTextColor(Color.BLUE)))
    }
}