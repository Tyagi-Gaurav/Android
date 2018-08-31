package com.antz.workout

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity(), Listener {
    override fun itemClicked(id: Int) {
        val fragmentView = findViewById<View>(R.id.fragment_container)

        Log.i("MainActivity", resources.getString(R.string.selected_configuration))

        if (fragmentView == null) {
            Log.i("MainActivity", "Fragment View is null")
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, id)
            startActivity(intent)
        } else {
            Log.i("MainActivity", "Fragment View is NOT null")
            // Add Fragment to FrameLayout
            val transaction = supportFragmentManager.beginTransaction()
            val workoutDetailFragment = WorkoutDetailFragment()
            workoutDetailFragment.setWorkout(id)
            transaction.replace(R.id.fragment_container, workoutDetailFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
