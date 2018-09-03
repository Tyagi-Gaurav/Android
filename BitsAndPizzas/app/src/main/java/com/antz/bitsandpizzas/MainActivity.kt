package com.antz.bitsandpizzas

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.MenuItemCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.ShareActionProvider
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu!!.findItem(R.id.action_share)
        val actionProvider = MenuItemCompat.getActionProvider(menuItem) as ShareActionProvider
        shareIntent(actionProvider, "Want to share Pizza !!")
        return super.onCreateOptionsMenu(menu)
    }

    fun shareIntent(actionProvider: ShareActionProvider, text : String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, text)
        actionProvider.setShareIntent(intent)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_create_order -> {
                val intent = Intent(this, OrderActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    class SectionsPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(p0: Int): Fragment {
            Log.i("SectionPagerAdapter", "Requested position $p0")
            when (p0) {
                0 -> return TopFragment()
                1 -> return PizzaFragment()
                2 -> return PastaFragment()
                else -> return StoresFragment()
            }
        }

        override fun getCount(): Int = 4
    }
}
