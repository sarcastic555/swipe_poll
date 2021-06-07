package com.example.swipepoll.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swipepoll.R
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.widget.*
import com.google.android.material.internal.ContextUtils.getActivity
import kotlin.random.Random

class PollFragmentEntry : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun generateDrawables() : Pair<Int, Int> {
        val rand = Random.nextInt(10)
        var drawable1 = R.drawable.toyota
        var drawable2: Int = R.drawable.nissan
        if (rand == 0) {
            drawable1 = R.drawable.kindle
            drawable2 = R.drawable.book
        } else if (rand == 1) {
            drawable1 = R.drawable.google
            drawable2 = R.drawable.yahoo
        } else if (rand == 2) {
            drawable1 = R.drawable.toyota
            drawable2 = R.drawable.nissan
        } else if (rand == 3) {
            drawable1 = R.drawable.air_conditioner
            drawable2 = R.drawable.fan
        } else if (rand == 4) {
            drawable1 = R.drawable.facebook
            drawable2 = R.drawable.twitter
        } else if (rand == 5) {
            drawable1 = R.drawable.kimetsu
            drawable2 = R.drawable.one_piece
        } else if (rand == 6) {
            drawable1 = R.drawable.sentochihiro
            drawable2 = R.drawable.totoro
        } else if (rand == 7) {
            drawable1 = R.drawable.short_cake
            drawable2 = R.drawable.monburan
        } else if (rand == 8) {
            drawable1 = R.drawable.smartphone
            drawable2 = R.drawable.computer
        } else if (rand == 9) {
            drawable1 = R.drawable.dog
            drawable2 = R.drawable.cat
        }
        return Pair(drawable1, drawable2)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_poll_entry, container, false)

        // 画像の設定
        val image1 = view.findViewById<ImageView>(R.id.imageView)
        val image2 = view.findViewById<ImageView>(R.id.imageView2)
        val (drawable1, drawable2) = generateDrawables()
        val bmp1 = BitmapFactory.decodeResource(
            this.resources,
            drawable1
        )
        val bmp2 = BitmapFactory.decodeResource(
            this.resources,
            drawable2
        )
        image1.setImageBitmap(bmp1)
        image2.setImageBitmap(bmp2)

        val bundle = Bundle()
        bundle.putParcelable("image1", bmp1)
        bundle.putParcelable("image2", bmp2)

        val resultFragment = ResultFragment()
        resultFragment.arguments = bundle

        // 選択ボタン
        val selectButton1 = view.findViewById<Button>(R.id.button4)
        selectButton1.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentPoll, resultFragment)
            transaction?.commit()
        }
        val selectButton2 = view.findViewById<Button>(R.id.button5)
        selectButton2.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentPoll, resultFragment)
            transaction?.commit()
        }

        return view
    }

}