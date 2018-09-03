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
class StoresFragment : ListFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val arrayAdapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1,
                resources.getStringArray(R.array.stores))

        listAdapter = arrayAdapter
        return super.onCreateView(inflater, container, savedInstanceState)
        //return inflater.inflate(R.layout.fragment_stores, container, false)
    }
}
