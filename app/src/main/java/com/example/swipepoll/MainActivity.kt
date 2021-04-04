package com.example.swipepoll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinnerItems = arrayOf(
            "Spinner",
            "Android",
            "Apple",
            "Windows"
        )
        val adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item,
            spinnerItems
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner = findViewById<Spinner>(R.id.spinner3)
        spinner.adapter = adapter
        //val adapter =
        //ArrayAdapter<String>(this, R.layout.simple_spinner_item)
        //adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        //adapter.add("A型")
        //adapter.add("B型")
        //adapter.add("AB型")
        //adapter.add("O型")
        //val spinner = findViewById<View>(R.id.spinner3) as Spinner
        //spinner.adapter = adapter
    }
}