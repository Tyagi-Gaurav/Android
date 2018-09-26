package com.antz.bitsandpizzas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
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

        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, baseContext)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)
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

    class SectionsPagerAdapter(fm : FragmentManager, private val context: Context) : FragmentPagerAdapter(fm) {
        override fun getItem(p0: Int): Fragment {
            Log.i("SectionPagerAdapter", "Requested position $p0")
            return when (p0) {
                0 -> TopFragment()
                1 -> PizzaFragment()
                2 -> PastaFragment()
                else -> StoresFragment()
            }
        }

        override fun getCount(): Int = 4

        override fun getPageTitle(position: Int): CharSequence? {
            return when(position) {
                0 -> context.resources.getString(R.string.home_tab)
                1 -> context.resources.getString(R.string.pizza_tab)
                2 -> context.resources.getString(R.string.pasta_tab)
                else -> context.resources.getString(R.string.store_tab)
            }
        }
    }
}
