package com.antz.bitsandpizzas


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 *
 */
class PizzaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val pizzaRecycler =
                inflater.inflate(R.layout.fragment_pizza, container, false) as RecyclerView

        val pizzaNames = Pizza.pizzas.map(Pizza::name).toTypedArray()
        val pizzaImages = Pizza.pizzas.map(Pizza::resourceId).toTypedArray()

        val captionedImagesAdapter = CaptionedImagesAdapter(pizzaNames, pizzaImages)
        captionedImagesAdapter.setListener(object : Listener {
            override fun onClick(position: Int) {
                val intent = Intent(activity, PizzaDetailActivity::class.java)
                intent.putExtra("EXTRA_PIZZA_ID", position)
                startActivity(intent)
            }
        })
        pizzaRecycler.adapter = captionedImagesAdapter
        pizzaRecycler.layoutManager = GridLayoutManager(activity, 2)
        return pizzaRecycler
    }
}
