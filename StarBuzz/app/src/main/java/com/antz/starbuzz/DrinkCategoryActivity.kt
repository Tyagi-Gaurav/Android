package com.antz.starbuzz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

class DrinkCategoryActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_category)

        val arrayAdapter = ArrayAdapter<Drink>(this@DrinkCategoryActivity,
                android.R.layout.simple_list_item_1, Drink.drinks
        )

        val listView = findViewById<ListView>(R.id.list_drinks)
        listView.setAdapter(arrayAdapter)

        val itemOnClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, itemView: View?, position: Int, id: Long) {
                val intent = Intent(this@DrinkCategoryActivity,
                        DrinkActivity::class.java)
                intent.putExtra(DrinkActivity.EXTRA_DRINK_ID, position)
                startActivity(intent)
            }
        }

        listView.setOnItemClickListener(itemOnClickListener)
    }
}
