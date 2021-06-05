package com.example.swipepoll.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
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

        // 選択肢の文字を表示
        val questionText = view.findViewById<TextView>(R.id.textView3)
        questionText.setText("どっちが好き？")

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
            transaction?.commit()
        }

        return view
    }
}