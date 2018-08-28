package com.antz.starbuzz

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DrinkActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink)

        val drinkId = intent.getIntExtra(EXTRA_DRINK_ID, -1)
        val drink = Drink.drinks.get(drinkId)
        val nameView = findViewById<TextView>(R.id.name)
        val descriptionView = findViewById<TextView>(R.id.description)
        val imageView = findViewById<ImageView>(R.id.photo)

        nameView.setText(drink.name)
        descriptionView.setText(drink.description)

        imageView.setImageResource(drink.resourceId)
        imageView.setContentDescription(drink.name)
    }

    companion object {
        val EXTRA_DRINK_ID  = "DrinkId"
    }
}
