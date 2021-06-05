package com.example.swipepoll.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swipepoll.R
import android.content.Context
import android.os.Handler
import android.widget.*
import com.google.android.material.internal.ContextUtils.getActivity

class PollFragmentEntry : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_poll_entry, container, false)

        // 選択ボタン
        val selectButton1 = view.findViewById<Button>(R.id.button4)
        selectButton1.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentPoll, ResultFragment())
            transaction?.commit()
        }
        val selectButton2 = view.findViewById<Button>(R.id.button5)
        selectButton2.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentPoll, ResultFragment())
            transaction?.commit()
        }

        return view
    }

}