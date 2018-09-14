package com.antz.starbuzz

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

val DB_NAME = "starbuzz"
val DB_VERSION = 3

val TABLE_CREATE = "CREATE TABLE DRINK " +
                   "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   "NAME TEXT, " +
                   "DESCRIPTION TEXT, " +
                   "IMAGE_RESOURCE_ID INTEGER );"

class StarBuzzDatabaseHelper(context: Context?) :
        SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        updateMyDatabase(db, oldVersion, newVersion)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        updateMyDatabase(db, 0 , DB_VERSION)
    }

    private fun updateMyDatabase(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.i("Database Upgrade/Create", "oldVersion $oldVersion, newVersion $newVersion")
        if (oldVersion < 2) {
                    db?.execSQL(TABLE_CREATE)
        insertDrink(db, "Latte",
                "A couple of espresso shots with steamed milk",
                R.drawable.latte)
        insertDrink(db, "Cappuccino", "Espresso, hot milk, and a steamed milk foam",
                R.drawable.cappuccino)
        insertDrink(db, "Filter", "Highest quality beans roasted and brewed fresh",
                R.drawable.filter)
        }

        if (newVersion == DB_VERSION) {
            db?.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;")
        }
    }

    private fun insertDrink(db: SQLiteDatabase?, name: String, description: String, imageResourceId: Int) {
        val contentValues = ContentValues()
        contentValues.put("NAME", name)
        contentValues.put("DESCRIPTION", description)
        contentValues.put("IMAGE_RESOURCE_ID", imageResourceId)
        db?.insert("DRINK", null, contentValues)
    }

}