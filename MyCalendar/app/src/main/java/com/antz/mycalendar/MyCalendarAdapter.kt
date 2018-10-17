package com.antz.mycalendar

import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask
import android.os.Handler
import android.provider.CalendarContract
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.lang.ref.WeakReference
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

class MyCalendarAdapter(private val activity: WeakReference<MainActivity>,
                        private val year : Int,
                        private val month : Int) : Handler() {

    private val EVENT_PROJECTION: Array<String> = arrayOf(
            CalendarContract.Events.CALENDAR_ID, //0
            CalendarContract.Events._ID, //1
            CalendarContract.Events.ACCOUNT_NAME, //2
            CalendarContract.Events.ACCOUNT_TYPE, //3
            CalendarContract.Events.TITLE, //4
            CalendarContract.Events.DESCRIPTION, //5
            CalendarContract.Events.DTSTART, //6
            CalendarContract.Events.DTEND //7
    )

    companion object {
        // The indices for the projection array above.
        private const val PERMISSION_REQUEST_CODE = 689
        private const val READ_CALENDAR_PERMISSION_STRING =
                android.Manifest.permission.READ_CALENDAR
    }

    fun getEvents() {
        val eventList = mutableListOf<EventModel>()
        // Run query
        if (ContextCompat.checkSelfPermission(activity.get()!!.applicationContext, READ_CALENDAR_PERMISSION_STRING) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity.get()!!, arrayOf(READ_CALENDAR_PERMISSION_STRING),
                    PERMISSION_REQUEST_CODE
            )
        } else {
            val uri: Uri = CalendarContract.Events.CONTENT_URI
            val selection: String =
                    "(" +
                    "(${CalendarContract.Events.ACCOUNT_NAME} = ?) AND (" +
                    "${CalendarContract.Events.ACCOUNT_TYPE} = ?) AND (" +
                    "${CalendarContract.Events.DTSTART} >= ?) AND (" +
                    "${CalendarContract.Events.DTEND} <= ?) AND (" +
                    "${CalendarContract.Events.OWNER_ACCOUNT} = ?)" +
                    ")"

            val maxDaysInMonth = CalendarGenerator.getNumberOfDaysIn(month, year)
            val startDate = GregorianCalendar(year, month, 1, 0, 0 , 0)
            val endDate = GregorianCalendar(year, month, maxDaysInMonth, 23, 59, 59)
            val selectionArgs: Array<String> = arrayOf("chonku@gmail.com",
                                                       "com.google",
                                                        startDate.timeInMillis.toString(),
                                                        endDate.timeInMillis.toString(),
                                                       "chonku@gmail.com")
            val cur: Cursor = (activity.get()!!.contentResolver as ContentResolver)
                    .query(uri, EVENT_PROJECTION, selection, selectionArgs, null)

            Log.d("MyCalendar", "Cursor ${cur.count}")

            while (cur.moveToNext()) {
                //Get the field values
                val eventId: String = getField(cur, 1)
                val title: String = getField(cur, 4)
                val dtStart: String = getDateTime(getField(cur, 6))
                val dtEnd: String = getDateTime(getField(cur, 7))

                //Do something with the values...
                eventList.add(EventModel(eventId, title, dtStart, dtEnd))
            }

            cur.close()
        }

        activity.get()!!.updateModel(eventList)
    }

    private fun getField(cur : Cursor, index : Int) =
            if (cur.getType(index) == Cursor.FIELD_TYPE_NULL)
                "" else cur.getString(index)

    private fun getDateTime(s: String): String {
        return try {
            val sdf = SimpleDateFormat("d/M/yyyy", Locale.getDefault())
            val netDate = Date(s.toLong())
            sdf.format(netDate)
        } catch (e: Exception) {
            ""
        }
    }
}