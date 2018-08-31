package com.antz.workout


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


private const val WORKOUT_ID = "workoutId"

/**
 * A simple [Fragment] subclass.
 *
 */
class WorkoutDetailFragment : Fragment() {

    var workoutId : Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false)
    }

    fun setWorkout(id : Int) {workoutId = id}

    override fun onStart() {
        super.onStart()
        view?.let {
            val textTitle = it.findViewById<TextView>(R.id.textTitle)
            val textDescription = it.findViewById<TextView>(R.id.textDescription)
            val workout = workouts[workoutId ?: 0]
            textTitle.setText(workout.name)
            textDescription.setText(workout.description)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(WORKOUT_ID, workoutId ?: 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            workoutId = savedInstanceState.getInt(WORKOUT_ID)
        }
    }
}
