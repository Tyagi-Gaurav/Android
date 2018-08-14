package com.antz.beeradvisor

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val beerExpert = BeerExpert()

    class BeerExpert {
        fun getBrands(beerType: String): String {
            val result = StringBuilder()
            when (beerType) {
                "amber" ->  result.append("Jack amber\n").append("Red Moose\n")
                else ->  result.append("Jail Pale Ale\n").append("Gout stout\n")
            }

            return result.toString()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickFindBeer(view : View) {
        val brands = findViewById<TextView>(R.id.brands)
        val color = findViewById<Spinner>(R.id.color)

        val beerType = color.selectedItem.toString()

        brands.text = beerExpert.getBrands(beerType)
    }

}
