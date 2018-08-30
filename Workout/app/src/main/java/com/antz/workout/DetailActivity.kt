package com.antz.workout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class DetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_WORKOUT_ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intExtra = intent.getIntExtra(EXTRA_WORKOUT_ID, 0)

        val detailFragment =
                supportFragmentManager.findFragmentById(R.id.detailFragment) as WorkoutDetailFragment

        detailFragment.setWorkout(intExtra)
    }
}
