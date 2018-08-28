package com.antz.starbuzz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView

class TopLevelActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_level)

        val itemOnClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, itemView: View?, position: Int, id: Long) {
                if (position == 0) {
                    val intent = Intent(this@TopLevelActivity, DrinkCategoryActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        val listView = findViewById<ListView>(R.id.list_options)
        listView.setOnItemClickListener(itemOnClickListener)
    }
}
