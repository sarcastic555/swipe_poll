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


    //private inner class ListitemClickListner : AdapterView.OnItemClickListener{
        //override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            //TODO("Not yet implemented")
            //val item = parent.getItemAtPosition(position) as MutableMap <String, Any>
            //val image1 = item["image1"]
            //val image2 = item["image2"]

            //val intent2MenuThanks = Intent(this@ListFragmentEntry, ResultFragment::class.java)
            //intent2MenuThanks.putExtra("image1",image1)
            //intent2MenuThanks.putExtra("image2",image2)
            //ResultFragment(intent2MenuThanks)
     //   }
    //}


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_entry, container, false)

        // ListViewのテスト (see p151)
        //val listView = view.findViewById<ListView>(R.id.listTest)
        //val pollList: MutableList<MutableMap<Any,Any,Any> > = mutableListOf()
        //var menu = mutableMapOf("pic1" to "からあげ","tex" to "vs", "pic2" to "880円")
        //pollList.add(menu)
        //menu = mutableMapOf("pic1" to "からあげ","tex" to "vs2", "pic2" to "880円")
        //pollList.add(menu)
        //val from = arrayOf("pic1","tex","pic2")
        //val to = intArrayOf(R.id.ivpicture1,R.id.tvexplanation,R.id.ivpicture2)
        // ImageViewとTextViewをもつレイアウトファイルを作成し、ここのsimple_list_item_2の部分を置き換えればいいはず
        //val adapter = SimpleAdapter(getActivity()?.applicationContext, pollList, R.layout.inside_list, from, to)
        //listView.adapter = adapter

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

        val from = arrayOf("image1", "tex", "image2")
        val to = intArrayOf(R.id.imageView3,R.id.textView5,R.id.imageView5)
        // ImageViewとTextViewをもつレイアウトファイルを作成し、ここのsimple_list_item_2の部分を置き換えればいいはず
        val adapter = SimpleAdapter(getActivity()?.applicationContext, pollList, R.layout.inside_list2, from, to)
        listView.adapter = adapter



        listView.setOnItemClickListener { parent, view, position, id ->
            /*
            val image1 = view.findViewById<ImageView>(R.id.imageView3)
            val image2 = view.findViewById<ImageView>(R.id.imageView5)
            val (drawable1, drawable2) = generateDrawables()
            val bmp1 = BitmapFactory.decodeResource(
                this.resources,
                drawable1
                )
            val bmp2 = BitmapFactory.decodeResource(
                this.resources,
                drawable2
                )
             */

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