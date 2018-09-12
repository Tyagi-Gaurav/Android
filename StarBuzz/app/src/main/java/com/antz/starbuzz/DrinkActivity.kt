package com.antz.starbuzz

import android.app.Activity
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class DrinkActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)

        val drinkId = intent.getIntExtra(EXTRA_DRINK_ID, -1)
        Log.i("DrinkActivity", "Drink ID: $drinkId.toString()")

        val starBuzzDatabaseHelper = StarBuzzDatabaseHelper(this)
        val readableDatabase = starBuzzDatabaseHelper.readableDatabase
        try {
            val cursor = readableDatabase.query("DRINK",
                    arrayOf("NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"),
                    "_id = ?",
                    arrayOf(drinkId.toString()),
                    null, null, null)

            val nameView = findViewById<TextView>(R.id.name)
            val descriptionView = findViewById<TextView>(R.id.description)
            val imageView = findViewById<ImageView>(R.id.photo)

            if (cursor.moveToFirst()) {
                val drinkName = cursor.getString(0)
                nameView.text = drinkName
                descriptionView.text = cursor.getString(1)
                imageView.setImageResource(cursor.getInt(2))
                imageView.contentDescription = drinkName
            }


            cursor.close()
            starBuzzDatabaseHelper.close()
        } catch (e : SQLiteException) {
            val makeText = Toast.makeText(this, "Database Unavailable", Toast.LENGTH_SHORT)
            makeText.show()
        }
    }

    companion object {
        val EXTRA_DRINK_ID  = "DrinkId"
    }
}
