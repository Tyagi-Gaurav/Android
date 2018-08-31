package com.antz.workout


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

/**
 * A simple [Fragment] subclass.
 *
 */
class WorkoutListFragment : ListFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val toTypedArray = workouts.map { it.name }.toTypedArray()

        val arrayAdapter = ArrayAdapter(inflater.context, android.R.layout.simple_list_item_1, toTypedArray)
        listAdapter = arrayAdapter

        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private lateinit var listener: Listener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.listener = context as Listener
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        listener?.let { listener.itemClicked(id.toInt()) }
    }
}
