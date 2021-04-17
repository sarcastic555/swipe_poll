package com.example.swipepoll

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import java.io.FileDescriptor
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private val RESULT_PICK_IMAGEFILE = 1000
    private var bmp1: Bitmap? = null
    private var bmp2: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setScreenMain();
    }

    private fun setScreenMain() {
        setContentView(R.layout.activity_main)
        var progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.GONE

        // 画像アップロードボタン
        val uploadButton1 = findViewById<Button>(R.id.button4)
        uploadButton1.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }
            startActivityForResult(intent, 1)
        }
        val uploadButton2 = findViewById<Button>(R.id.button5)
        uploadButton2.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }
            startActivityForResult(intent, 2)
        }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            //var uri: Uri
            if (resultData != null) {
                try {
                    var uri: Uri? = resultData.data
                    if (requestCode == 1) {
                        bmp1 = getBitmapFromUri(uri)
                        val imageView = findViewById<ImageView>(R.id.imageView);
                        imageView.setImageBitmap(bmp1)
                    } else if (requestCode == 2) {
                        bmp2 = getBitmapFromUri(uri)
                        val imageView = findViewById<ImageView>(R.id.imageView2);
                        imageView.setImageBitmap(bmp2)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun getBitmapFromUri(uri: Uri?): Bitmap? {
        if (uri == null) return null
        val parcelFileDescriptor =
            contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
        val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor.close()
        return image
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

        // 画像の設定
        val imageView1 = findViewById<ImageView>(R.id.imageView);
        imageView1.setImageBitmap(bmp1)
        val imageView2 = findViewById<ImageView>(R.id.imageView2);
        imageView2.setImageBitmap(bmp2)

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