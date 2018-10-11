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
            CalendarContract.Calendars._ID,                     // 0
            CalendarContract.Calendars.ACCOUNT_NAME,            // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,   // 2
            CalendarContract.Calendars.OWNER_ACCOUNT,            // 3
            CalendarContract.Calendars.ACCOUNT_TYPE            // 4

    )

    companion object {
        // The indices for the projection array above.
        private const val PERMISSION_REQUEST_CODE = 689
        private const val READ_CALENDAR_PERMISSION_STRING = android.Manifest.permission.READ_CALENDAR
        private const val PROJECTION_ID_INDEX: Int = 0
        private const val PROJECTION_ACCOUNT_NAME_INDEX: Int = 1
        private const val PROJECTION_DISPLAY_NAME_INDEX: Int = 2
        private const val PROJECTION_OWNER_ACCOUNT_INDEX: Int = 3
        private const val PROJECTION_ACCOUNT_TYPE_INDEX: Int = 4
    }

    fun getEvents() {
        // Run query
        if (ContextCompat.checkSelfPermission(activity.applicationContext, READ_CALENDAR_PERMISSION_STRING) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(READ_CALENDAR_PERMISSION_STRING),
                    PERMISSION_REQUEST_CODE
            )
        } else {
            val uri: Uri = CalendarContract.Calendars.CONTENT_URI
            val selection: String = "((${CalendarContract.Calendars.ACCOUNT_NAME} = ?) AND (" +
                    "${CalendarContract.Calendars.ACCOUNT_TYPE} = ?) AND (" +
                    "${CalendarContract.Calendars.OWNER_ACCOUNT} = ?))"
            val selectionArgs: Array<String> = arrayOf("chonku@gmail.com", "com.google", "chonku@gmail.com")
//            val selection = "(${CalendarContract.Calendars.VISIBLE} = ?)"
//            val selectionArgs: Array<String> = arrayOf("1")
            val cur: Cursor = contentResolver.query(uri, EVENT_PROJECTION, selection, selectionArgs, null)

            while (cur.moveToNext()) {
                // Get the field values
                val calID: Long = cur.getLong(PROJECTION_ID_INDEX)
                val displayName: String = cur.getString(PROJECTION_DISPLAY_NAME_INDEX)
                val accountName: String = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX)
                val ownerName: String = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX)
                val accountType: String = cur.getString(PROJECTION_ACCOUNT_TYPE_INDEX)
                // Do something with the values...

                Log.d("MyCalendar", "$calID, $displayName, $accountName, $ownerName, $accountType")
            }

            Log.d("MyCalendar", "Cursor ${cur.count}")

            cur.close()
        }
    }

}