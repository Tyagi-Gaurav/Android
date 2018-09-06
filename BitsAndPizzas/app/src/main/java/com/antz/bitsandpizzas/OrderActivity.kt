package com.antz.bitsandpizzas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun onClickDone(view : View) {
        val s = "Your order has been updated"
        val duration = Snackbar.LENGTH_SHORT
        val snackBar = Snackbar.make(findViewById(R.id.order_coordinator), s, duration)
        snackBar.setAction("Undo", View.OnClickListener {
            val makeText = Toast.makeText(this, "Undone !", Toast.LENGTH_SHORT)
            makeText.show()
        })

        snackBar.show()
    }
}
