package com.antz.mycalendar

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainToolbar = findViewById<Toolbar>(R.id.toolbar_main)
        setSupportActionBar(mainToolbar)

        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        val tabs = findViewById<TabLayout>(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        viewPager.adapter = SectionsPagerAdapter(supportFragmentManager, this)
    }

    class SectionsPagerAdapter(fm : FragmentManager, private val context : Context) :
            FragmentPagerAdapter(fm) {
        override fun getItem(p0: Int): Fragment {
            return MonthFragment()
        }

        override fun getCount(): Int = 3

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> context.resources.getString(R.string.month)
                1 -> context.resources.getString(R.string.week)
                else -> context.resources.getString(R.string.year)
            }
        }
    }
}
