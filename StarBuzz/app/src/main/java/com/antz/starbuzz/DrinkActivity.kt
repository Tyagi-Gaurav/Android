package com.antz.starbuzz

import android.app.Activity
import android.content.ContentValues
import android.database.sqlite.SQLiteException
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
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
                    arrayOf("NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"),
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

                val isFavorite = cursor.getInt(3) == 1
                val favCheckBox = findViewById<CheckBox>(R.id.favorite)
                favCheckBox.isChecked = isFavorite
            }

            cursor.close()
            starBuzzDatabaseHelper.close()
        } catch (e : SQLiteException) {
            val makeText = Toast.makeText(this, "Database Unavailable", Toast.LENGTH_SHORT)
            makeText.show()
        }
    }

    fun onFavoriteClicked(view : View) {
        val drinkId = intent.getIntExtra(EXTRA_DRINK_ID, -1)
        UpdateDrinkTask().execute(drinkId)
    }

    private inner class UpdateDrinkTask : AsyncTask<Int, Unit, Boolean>() {

        private lateinit var drinkValues: ContentValues


        override fun onPreExecute() {
            val favoriteCheckBox = findViewById<CheckBox>(R.id.favorite)
            drinkValues = ContentValues()
            drinkValues.put("FAVORITE", favoriteCheckBox.isChecked)
        }

        override fun doInBackground(vararg params: Int?): Boolean {
            params.let {
                params[0].let {
                    try {
                        val starBuzzDatabaseHelper = StarBuzzDatabaseHelper(this@DrinkActivity)
                        val writableDatabase = starBuzzDatabaseHelper.writableDatabase
                        writableDatabase.update("DRINK", drinkValues, "_id = ?",
                                arrayOf(it.toString()))
                        return true
                    } catch(e : SQLiteException) {
                        return false
                    }
                }
            }
        }

        override fun onPostExecute(result: Boolean?) {
            if (result == null || !result) {
                val makeText = Toast.makeText(this@DrinkActivity, "System error Occurred", Toast.LENGTH_SHORT)
                makeText.show()
            }
        }
    }

    companion object {
        val EXTRA_DRINK_ID  = "DrinkId"
    }
}
