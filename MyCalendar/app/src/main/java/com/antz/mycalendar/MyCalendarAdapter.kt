package com.antz.mycalendar

import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MyCalendarAdapter(private val contentResolver: ContentResolver,
                        private val activity: AppCompatActivity) {

    private val EVENT_PROJECTION: Array<String> = arrayOf(
            CalendarContract.Events.CALENDAR_ID,
            CalendarContract.Events._ID,
            CalendarContract.Events.ACCOUNT_NAME,
            CalendarContract.Events.ACCOUNT_TYPE,
            CalendarContract.Events.TITLE
    )

    companion object {
        // The indices for the projection array above.
        private const val PERMISSION_REQUEST_CODE = 689
        private const val READ_CALENDAR_PERMISSION_STRING = android.Manifest.permission.READ_CALENDAR
    }

    fun getEvents() {
        // Run query
        if (ContextCompat.checkSelfPermission(activity.applicationContext, READ_CALENDAR_PERMISSION_STRING) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(READ_CALENDAR_PERMISSION_STRING),
                    PERMISSION_REQUEST_CODE
            )
        } else {
            val uri: Uri = CalendarContract.Events.CONTENT_URI
            val selection: String = "((${CalendarContract.Events.ACCOUNT_NAME} = ?) AND (" +
                    "${CalendarContract.Events.ACCOUNT_TYPE} = ?) AND (" +
                    "${CalendarContract.Events.OWNER_ACCOUNT} = ?))"
            val selectionArgs: Array<String> = arrayOf("chonku@gmail.com", "com.google", "chonku@gmail.com")
            val cur: Cursor = contentResolver.query(uri, EVENT_PROJECTION, selection, selectionArgs, null)

            while (cur.moveToNext()) {
                // Get the field values
                val calID: Long = cur.getLong(0)
                val eventId: String = cur.getString(1)
                val title: String = cur.getString(4)
                // Do something with the values...

                Log.d("MyCalendar", "$eventId, $title")
            }

            Log.d("MyCalendar", "Cursor ${cur.count}")

            cur.close()
        }
    }

}