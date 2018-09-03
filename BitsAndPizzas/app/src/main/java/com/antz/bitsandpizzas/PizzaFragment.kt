package com.antz.bitsandpizzas


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

/**
 * A simple [Fragment] subclass.
 *
 */
class PizzaFragment : ListFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val arrayAdapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1,
                resources.getStringArray(R.array.pizzas))
        listAdapter = arrayAdapter
        //return inflater.inflate(R.layout.fragment_pizza, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
