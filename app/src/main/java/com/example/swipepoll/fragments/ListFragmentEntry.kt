package com.example.swipepoll.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.swipepoll.R
import java.io.ByteArrayOutputStream
import java.io.FileDescriptor
import java.io.IOException

class ListFragmentEntry : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_entry, container, false)

        // ListViewのテスト (see p151)
        val listView = view.findViewById<ListView>(R.id.listTest)
        val menuList: MutableList<MutableMap<String, String> > = mutableListOf()
        var menu = mutableMapOf("name" to "からあげ", "price" to "880円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ハンバーグ定食", "price" to "850円")
        menuList.add(menu)
        val from = arrayOf("name", "price")
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        // ImageViewとTextViewをもつレイアウトファイルを作成し、ここのsimple_list_item_2の部分を置き換えればいいはず
        val adapter = SimpleAdapter(getActivity()?.applicationContext, menuList, android.R.layout.simple_list_item_2, from, to)
        listView.adapter = adapter

        return view
    }



}