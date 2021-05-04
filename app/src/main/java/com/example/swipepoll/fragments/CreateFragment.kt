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

class CreateFragment constructor(context: Context) : Fragment() {

    private var mycontext: Context
    private var spinnerText: String? = null
    init {
        this.mycontext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create, container, false)

        // ======== Initial setting ===========-
        var progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.GONE
        var progressText = view.findViewById<TextView>(R.id.textView)
        progressText.visibility = View.GONE

        // 画像アップロードボタン
        val uploadButton1 = view.findViewById<Button>(R.id.button4)
        uploadButton1.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }
            startActivityForResult(intent, 1)
        }
        val uploadButton2 = view.findViewById<Button>(R.id.button5)
        uploadButton2.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }
            startActivityForResult(intent, 2)
        }

        // Spinnerの設定
        val spinnerItems = arrayOf(
            "どちらが好き？",
            "どちらをよく使う？"
        )
        val adapter = ArrayAdapter(
            this.mycontext,
            R.layout.spinner_item,
            spinnerItems
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinner = view.findViewById<Spinner>(R.id.spinner3)
        spinner.adapter = adapter
        spinnerText = spinner.getSelectedItem().toString();
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                spinnerText = spinner.getSelectedItem().toString();
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        return view
    }

}