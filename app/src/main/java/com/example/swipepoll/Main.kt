package com.example.swipepoll

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout

class Main : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main)

    // TabLayoutの取得
    val tabLayout = findViewById<TabLayout>(R.id.tab_layout)

    // OnTabSelectedListenerの実装
    tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {

      // タブが選択された際に呼ばれる
      override fun onTabSelected(tab: TabLayout.Tab) {
        Toast.makeText(this@Main, tab.text, Toast.LENGTH_SHORT).show()
      }

      // タブが未選択になった際に呼ばれる
      override fun onTabUnselected(tab: TabLayout.Tab) {

      }

      // 同じタブが選択された際に呼ばれる
      override fun onTabReselected(tab: TabLayout.Tab) {

      }
    })
  }
}