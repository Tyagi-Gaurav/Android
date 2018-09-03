package com.antz.workout

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity(), Listener {
    override fun itemClicked(id: Int) {
        val fragmentView = findViewById<View>(R.id.fragment_container)

        if (fragmentView == null) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, id)
            startActivity(intent)
        } else {
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
