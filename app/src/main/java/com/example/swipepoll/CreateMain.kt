package com.example.swipepoll

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlin.random.Random
import java.io.FileDescriptor
import java.io.IOException


class CreateMain : AppCompatActivity() {
    private val RESULT_PICK_IMAGEFILE = 1000
    private var bmp1: Bitmap? = null
    private var bmp2: Bitmap? = null
    private var spinnerText: String? = null
    var setImage1 = false
    var setImage2 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setScreenMain();
    }

    private fun setScreenMain() {
        setContentView(R.layout.fragment_create)
        var progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.GONE
        var progressText = findViewById<TextView>(R.id.textView)
        progressText.visibility = View.GONE


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
            "どちらが好き？",
            "どちらをよく使う？"
        )
        val adapter = ArrayAdapter(
            applicationContext,
            R.layout.spinner_item,
            spinnerItems
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val spinner = findViewById<Spinner>(R.id.spinner3)
        spinner.adapter = adapter
        spinnerText = spinner.getSelectedItem().toString();
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
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

        class TextChangeRunnable() : Runnable {
            override fun run() {
                setScreenSub()
            }
        }

        val createButton = findViewById<Button>(R.id.button3);
        createButton.setOnClickListener {
            if (setImage1 && setImage2) {
                progressBar.visibility = View.VISIBLE
                progressText.visibility = View.VISIBLE
                val handler = Handler()
                handler.postDelayed(TextChangeRunnable(), 15000)
            }
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
                        setImage1 = true
                    } else if (requestCode == 2) {
                        bmp2 = getBitmapFromUri(uri)
                        val imageView = findViewById<ImageView>(R.id.imageView2);
                        imageView.setImageBitmap(bmp2)
                        setImage2 = true
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
        setContentView(R.layout.create_result)

        // 選択肢の文字を表示
        val questionText = findViewById<TextView>(R.id.textView3)
        questionText.setText(spinnerText)

        // 画像の設定
        val imageView1 = findViewById<ImageView>(R.id.imageView);
        imageView1.setImageBitmap(bmp1)
        val imageView2 = findViewById<ImageView>(R.id.imageView2);
        imageView2.setImageBitmap(bmp2)

        // 投票数を設定
        val pollnumTotal = Random.nextInt(10, 20)
        val pollText = findViewById<TextView>(R.id.textView2);
        pollText.setText("投票数 %d".format(pollnumTotal))

        // 投票割合を確定
        val pollnumA = Random.nextInt(pollnumTotal+1)
        val pollnumB = pollnumTotal - pollnumA
        val pollratioA = (pollnumA * 100.0 / pollnumTotal).toInt()
        val pollratioB = 100 - pollratioA
        val displayA = "%d%%".format(pollratioA)
        val displayB = "%d%%".format(pollratioB)

        // Graph
        val dimensions = listOf(displayA, displayB)//分割円の名称(String型)
        val values = listOf(pollnumA.toFloat(), pollnumB.toFloat())//分割円の大きさ(Float型)
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
        createButton.setOnClickListener {
            setImage1 = false
            setImage2 = true
            setScreenMain()
        };
    }
}