package com.example.swipepoll

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Spinner
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setScreenMain();
    }

    private fun setScreenMain() {
        setContentView(R.layout.activity_main)
        var progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.GONE

        // Spinnerの設定
        val spinnerItems = arrayOf(
            "Select your question",
            "Which one do you like?",
            "Which one do you use more often?",
            "Which one do you have?"
        )
        val adapter = ArrayAdapter(
            applicationContext,
            R.layout.spinner_item,
            spinnerItems
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinner = findViewById<Spinner>(R.id.spinner3)
        spinner.adapter = adapter

        class TextChangeRunnable() : Runnable {
            override fun run() {
                setScreenSub()
            }
        }

        val createButton = findViewById<Button>(R.id.button3);
        createButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val handler = Handler()
            handler.postDelayed(TextChangeRunnable(), 3000)
        };
    }

    private fun setScreenSub() {
        setContentView(R.layout.activity_sub)
        // Spinnerの設定
        val spinnerItems = arrayOf(
            "Select your question",
            "Which one do you like?",
            "Which one do you use more often?",
            "Which one do you have?"
        )
        val adapter = ArrayAdapter(
            applicationContext,
            R.layout.spinner_item,
            spinnerItems
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinner = findViewById<Spinner>(R.id.spinner3)
        spinner.adapter = adapter

        // Graph
        val dimensions = listOf("TOYOTA", "NISSAN")//分割円の名称(String型)
        val values = listOf(4f, 1f)//分割円の大きさ(Float型)
        val bgcolors = listOf(Color.RED, Color.BLUE)
        //①Entryにデータ格納
        var entryList = mutableListOf<PieEntry>()
        for(i in values.indices){
            entryList.add(
                PieEntry(values[i], dimensions[i])
            )
        }
        //②PieDataSetにデータ格納
        val pieDataSet = PieDataSet(entryList, "candle")
        //③DataSetのフォーマット指定
        //pieDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()
        pieDataSet.colors = bgcolors
        //④PieDataにPieDataSet格納
        val pieData = PieData(pieDataSet)
        //⑤PieChartにPieData格納
        val pieChart = findViewById<PieChart>(R.id.pieChartExample)
        pieChart.data = pieData
        //⑥Chartのフォーマット指定
        pieChart.legend.isEnabled = false
        pieChart.setEntryLabelTextSize(20f)
        //⑦PieChart更新
        pieChart.invalidate()

        val createButton = findViewById<Button>(R.id.button3);
        createButton.setOnClickListener {setScreenMain()};
    }
}