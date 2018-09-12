package com.antz.starbuzz

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast

class DrinkCategoryActivity : Activity() {
    private var cursor : Cursor? = null
    private var starBuzzDatabaseHelper : StarBuzzDatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_category)

        val listView = findViewById<ListView>(R.id.list_drinks)

        try {
            starBuzzDatabaseHelper = StarBuzzDatabaseHelper(this)
            val readableDatabase = starBuzzDatabaseHelper?.readableDatabase
            cursor = readableDatabase?.query("DRINK",
                    arrayOf("_id", "NAME"), null,
                    null, null, null, null)

            val simpleCursorAdapter = SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor,
                    arrayOf("NAME"), intArrayOf(android.R.id.text1), 0)

            listView.adapter = simpleCursorAdapter
        } catch (e : SQLiteException) {
            val makeText = Toast.makeText(this, "Database Unavailable", Toast.LENGTH_SHORT)
            makeText.show()
        }

        val itemOnClickListener = AdapterView.OnItemClickListener { _, _, _, id ->
            val intent = Intent(this@DrinkCategoryActivity,
                    DrinkActivity::class.java)
            intent.putExtra(DrinkActivity.EXTRA_DRINK_ID, id.toInt())
            startActivity(intent)
        }

        listView.onItemClickListener = itemOnClickListener
    }

    override fun onDestroy() {
        super.onDestroy()
        cursor?.close()
        starBuzzDatabaseHelper?.close()
    }
}
