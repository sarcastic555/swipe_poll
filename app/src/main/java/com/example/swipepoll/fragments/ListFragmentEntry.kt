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


        val listView = view.findViewById<ListView>(R.id.listTest)

        // add pictures in the list
        val pollList: MutableList<MutableMap<String,Any> > = mutableListOf()
        var menu = mutableMapOf("image1" to R.drawable.fan,"tex" to "vs","image2" to R.drawable.air_conditioner)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.dog, "tex" to "vs","image2" to R.drawable.cat)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.kimetsu , "tex" to "vs","image2" to R.drawable.one_piece)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.toyota, "tex" to "vs","image2" to R.drawable.nissan)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.totoro, "tex" to "vs","image2" to R.drawable.sentochihiro)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.kindle, "tex" to "vs","image2" to R.drawable.book)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.benz, "tex" to "vs","image2" to R.drawable.bmw)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.pepsi, "tex" to "vs","image2" to R.drawable.cocacola)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.bread, "tex" to "vs","image2" to R.drawable.rice)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.coffer, "tex" to "vs","image2" to R.drawable.cafelate)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.greenrascal, "tex" to "vs","image2" to R.drawable.redkitsune)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.chanel, "tex" to "vs","image2" to R.drawable.vuitton)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.baseball, "tex" to "vs","image2" to R.drawable.soccer)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.nike, "tex" to "vs","image2" to R.drawable.adidas)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.ooron, "tex" to "vs","image2" to R.drawable.greentea)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.kid, "tex" to "vs","image2" to R.drawable.konan)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.mos, "tex" to "vs","image2" to R.drawable.mac)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.udon, "tex" to "vs","image2" to R.drawable.soba)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.solt, "tex" to "vs","image2" to R.drawable.source)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.redwine, "tex" to "vs","image2" to R.drawable.whitewine)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.tealeaf, "tex" to "vs","image2" to R.drawable.teapet)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.tomato, "tex" to "vs","image2" to R.drawable.mayo)
        pollList.add(menu)
        menu = mutableMapOf("image1" to R.drawable.souna, "tex" to "vs","image2" to R.drawable.massage)
        pollList.add(menu)

        val from = arrayOf("image1", "tex", "image2")
        val to = intArrayOf(R.id.imageView3,R.id.textView5,R.id.imageView5)
        // ImageViewとTextViewをもつレイアウトファイルを作成し、ここのsimple_list_item_2の部分を置き換えればいいはず
        val adapter = SimpleAdapter(getActivity()?.applicationContext, pollList, R.layout.inside_list2, from, to)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->


            val item = parent.getItemAtPosition(position) as MutableMap <String, Any>

            val picture1: Int = item["image1"] as Int
            val picture2: Int = item["image2"] as Int

            val bmp1 = BitmapFactory.decodeResource(
                this.resources,
                picture1
            )
            val bmp2 = BitmapFactory.decodeResource(
                this.resources,
                picture2
            )

            val bundle = Bundle()
            bundle.putParcelable("image1", bmp1)
            bundle.putParcelable("image2", bmp2)
            bundle.putString("question", "Test")
            bundle.putString("origin", "list")

            val resultFragment = ResultFragment()
            resultFragment.arguments = bundle

            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentList, resultFragment)
            transaction?.commit()
        }

        return view
    }



}