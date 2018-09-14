package com.antz.starbuzz

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.widget.*

class TopLevelActivity : Activity() {

    lateinit var readableDatabase: SQLiteDatabase
    lateinit var starBuzzDatabaseHelper : StarBuzzDatabaseHelper
    lateinit var favoritesCursor : Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_level)
        setupOptionsListView()
        setupFavoritesListView()
    }

    private fun setupOptionsListView() {
        val itemOnClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            if (position == 0) {
                val intent = Intent(this@TopLevelActivity, DrinkCategoryActivity::class.java)
                startActivity(intent)
            }
        }

        val listView = findViewById<ListView>(R.id.list_options)
        listView.onItemClickListener = itemOnClickListener
    }

    private fun setupFavoritesListView() {
        val listFavorites = findViewById<ListView>(R.id.list_favorites)
        try {
            starBuzzDatabaseHelper = StarBuzzDatabaseHelper(this)
            readableDatabase = starBuzzDatabaseHelper.readableDatabase
            favoritesCursor = readableDatabase.query("DRINK",
                    arrayOf("_id", "NAME"),
                    "FAVORITE = 1",
                    null, null, null, null)

            val simpleCursorAdapter = SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    favoritesCursor,
                    arrayOf("NAME"),
                    intArrayOf(android.R.id.text1), 0)

            listFavorites.adapter = simpleCursorAdapter

            listFavorites.setOnItemClickListener{ _, _, _, id ->
                run {
                    val intent = Intent(this, DrinkActivity::class.java)
                    intent.putExtra(DrinkActivity.EXTRA_DRINK_ID, id.toInt())
                    startActivity(intent)
                }
            }
        } catch(exception : SQLiteException) {
            val makeText = Toast.makeText(this, "Database unavailable!", Toast.LENGTH_SHORT)
            makeText.show()
        }
    }

    override fun onRestart() {
        super.onRestart()
        val newCursor = readableDatabase.query("DRINK",
                arrayOf("_id", "NAME"),
                "FAVORITE = 1",
                null, null, null, null)
        val listFavorites = findViewById<ListView>(R.id.list_favorites)
        val adapter = listFavorites.adapter as CursorAdapter
        adapter.changeCursor(newCursor)
        favoritesCursor = newCursor
    }
}
