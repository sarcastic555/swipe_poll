package com.example.swipepoll

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.example.swipepoll.fragments.PollFragment
import com.example.swipepoll.fragments.CreateFragment
import com.example.swipepoll.fragments.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class Main : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main)

    setUpTabs()
  }

  private fun setUpTabs() {
    val adapter = ViewPagerAdapter(supportFragmentManager)
    adapter.addFragment(CreateFragment(), "Create")
    adapter.addFragment(PollFragment(), "Poll")

    val viewPager = findViewById<ViewPager>(R.id.viewPager)
    viewPager.adapter = adapter
    val tabs = findViewById<TabLayout>(R.id.tabs)
    tabs.setupWithViewPager(viewPager)
    tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_create_24)
    tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_back_hand_24)
  }
}