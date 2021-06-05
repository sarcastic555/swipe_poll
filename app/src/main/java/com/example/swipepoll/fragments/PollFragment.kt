package com.example.swipepoll.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swipepoll.R

class PollFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val transaction = fragmentManager?.beginTransaction()
        transaction?.add(R.id.fragmentPoll, PollFragmentEntry())
        transaction?.commit()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_poll, container, false)
    }
}