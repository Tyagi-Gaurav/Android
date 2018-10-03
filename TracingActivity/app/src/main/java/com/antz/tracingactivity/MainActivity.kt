package com.antz.tracingactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Tracing", "onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Tracing", "onRestart")
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        Log.d("Tracing", "onPostCreate")
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        Log.d("Tracing", "onPostCreate")
    }

    override fun onPostResume() {
        super.onPostResume()
        Log.d("Tracing", "onPostResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Tracing", "onDestroy")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Tracing", "onStart")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.d("Tracing", "onSaveInstanceState")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Tracing", "onStop")
    }
}
