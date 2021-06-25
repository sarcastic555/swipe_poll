package com.example.swipepoll.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swipepoll.R
import android.content.Context
import android.widget.*
import com.google.android.material.internal.ContextUtils.getActivity

class ListFragment constructor() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.add(R.id.fragmentList, ListFragmentEntry())
        transaction?.commit()
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

}