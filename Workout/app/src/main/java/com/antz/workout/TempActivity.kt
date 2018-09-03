package com.antz.workout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction

class TempActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp)

        if (savedInstanceState == null) {
            val stopWatchFragment = StopWatchFragment()
            val fragmentTransaction = supportFragmentManager.beginTransaction()

            fragmentTransaction.add(R.id.stopwatch_container, stopWatchFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            fragmentTransaction.commit()
        }

    }
}
