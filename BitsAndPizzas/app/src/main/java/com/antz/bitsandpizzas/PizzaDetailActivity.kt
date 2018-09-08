package com.antz.bitsandpizzas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView

class PizzaDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza_detail)

        //Set Toolbar as activity's app bar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        //Display details of the Pizza
        val pizzaId = intent.getIntExtra("EXTRA_PIZZA_ID", -1)
        val pizzaName = Pizza.pizzas[pizzaId].name
        val textView = findViewById<TextView>(R.id.pizza_text)
        textView.setText(pizzaName)
        val imageView = findViewById<ImageView>(R.id.pizza_image)
        imageView.setImageDrawable(ContextCompat.getDrawable(this, Pizza.pizzas[pizzaId].resourceId))
        imageView.contentDescription = pizzaName
    }
}
