package com.example.swipepoll.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.swipepoll.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlin.random.Random


class ResultFragment : Fragment() {

    companion object {
        fun newInstance(): ResultFragment
        {
            val resultFragment = ResultFragment()
            return resultFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //super.onCreate(null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        // Get bundle object
        val extras = arguments

        // 選択肢の文字を表示
        val questionText = view.findViewById<TextView>(R.id.textView3)
        questionText.setText(extras?.getString("question"))

        // 画像を表示
        val image1 = view.findViewById<ImageView>(R.id.imageView)
        val image2 = view.findViewById<ImageView>(R.id.imageView2)
        val bmp1: Bitmap? = extras?.getParcelable<Bitmap>("image1")
        val bmp2: Bitmap? = extras?.getParcelable<Bitmap>("image2")
        if (bmp1 != null && bmp2 != null) {
            image1.setImageBitmap(bmp1)
            image2.setImageBitmap(bmp2)
        }

        // 投票数を設定
        val pollnumTotal = Random.nextInt(10, 20)
        val pollText = view.findViewById<TextView>(R.id.textView2);
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
        val pieChart = view.findViewById<PieChart>(R.id.pieChartExample)
        pieChart.data = pieData
        //⑥Chartのフォーマット指定
        pieChart.legend.isEnabled = false
        pieChart.setEntryLabelTextSize(20f)
        //⑦PieChart更新
        pieChart.invalidate()

        // 戻るボタン
        val backButton = view.findViewById<Button>(R.id.button3)
        backButton.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentCreate, CreateFragmentEntry())
            transaction?.replace(R.id.fragmentPoll, PollFragmentEntry())
            transaction?.commit()
        }

        return view
    }
}