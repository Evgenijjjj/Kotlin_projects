package com.example.admin.usempandroidchart

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fun getLineChar(CUBIC_BEZIER_FLAG:Boolean){
            chart.setVisibility(View.INVISIBLE)
            bar_chart.setVisibility(View.INVISIBLE)
            line_chart.setVisibility(View.VISIBLE)

            val monthNames: Array<String> = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep","Oct",
                    "Nov", "Dec")

            val lineEntries = mutableListOf<Entry>()
            lineEntries.add(Entry(0f, 7f))
            lineEntries.add(Entry(1f, 2f))
            lineEntries.add(Entry(2f, 3f))
            lineEntries.add(Entry(3f, 4f))
            lineEntries.add(Entry(4f, 2f))
            lineEntries.add(Entry(5f, 3f))
            lineEntries.add(Entry(6f, 1f))
            lineEntries.add(Entry(7f, 5f))
            lineEntries.add(Entry(8f, 7f))
            lineEntries.add(Entry(9f, 6f))
            lineEntries.add(Entry(10f, 10f))
            lineEntries.add(Entry(11f, 5f))

            val lineDataSet = LineDataSet(lineEntries, "Oil Price")
            if (CUBIC_BEZIER_FLAG){
                line_chart.animateY(1000)
                lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER)
                lineDataSet.setDrawFilled(true)
            }else{
                line_chart.animateX(1000)
            }
            val lineData = LineData(lineDataSet)

            line_chart.getDescription().setText("Price in last 12 days")
            line_chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTH_SIDED)
            line_chart.setData(lineData)

            val xAxis = line_chart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.textSize = 12f
            xAxis.textColor = Color.RED
            xAxis.setValueFormatter(IndexAxisValueFormatter(monthNames))

            line_chart.invalidate()
        }

        button1.setOnClickListener {
            getLineChar(false)
        }

        button2.setOnClickListener {
            getLineChar(true)
        }

        button3.setOnClickListener {
            line_chart.setVisibility(View.INVISIBLE)
            bar_chart.setVisibility(View.VISIBLE)
            chart.setVisibility(View.INVISIBLE)

            val labels = ArrayList<String>()
            labels.add("January")
            labels.add("February")
            labels.add("March")
            labels.add("April")
            labels.add("May")
            labels.add("June")

            val lineEntries = mutableListOf<BarEntry>()
            lineEntries.add(BarEntry(0f, 24f))
            lineEntries.add(BarEntry(1f, 6f))
            lineEntries.add(BarEntry(2f, 9f))
            lineEntries.add(BarEntry(3f, 30f))
            lineEntries.add(BarEntry(4f, 15f))
            lineEntries.add(BarEntry(5f, 21f))


            val barDataSet = BarDataSet(lineEntries, "# of Calls")
            barDataSet.setColors(ColorTemplate.COLORFUL_COLORS,255)
            val data = BarData(barDataSet)
            bar_chart.setFitBars(true)
            bar_chart.setData(data)
            bar_chart.description.text = "# of times Alice called Bob"
            bar_chart.animateXY(1200,1200)

            val xAxis = bar_chart.xAxis
            xAxis.textSize = 10f
            xAxis.position = XAxis.XAxisPosition.TOP_INSIDE
            xAxis.setValueFormatter(IndexAxisValueFormatter(labels))

            bar_chart.invalidate()
        }

        button4.setOnClickListener {
            line_chart.setVisibility(View.INVISIBLE)
            bar_chart.setVisibility(View.INVISIBLE)
            chart.setVisibility(View.VISIBLE)

            val rainfall = floatArrayOf(50.0f, 125.0f, 162.0f, 60f,52f, 58.2f,120f,130f,125f,100f,
                    120f,40f)
            val monthNames: Array<String> = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep","Oct",
                    "Nov", "Dec")

            val pieEntries = List<PieEntry>(12, { i -> PieEntry(rainfall[i],monthNames[i])})
            val dataSet = PieDataSet(pieEntries,null)
            val colorsList = intArrayOf(
                    Color.rgb(192,192,192),
                    Color.rgb(128,128,128),
                    Color.rgb(0,0,0),
                    Color.rgb(128,0,0),
                    Color.rgb(255,0,0),
                    Color.rgb(255,165,0),
                    Color.rgb(128,128,0),
                    Color.rgb(0,255,0),
                    Color.rgb(0,255,255),
                    Color.rgb(0,0,255),
                    Color.rgb(0,0,128),
                    Color.rgb(255,0,255)
                    )
            dataSet.setColors(colorsList,255)
            chart.legend.setWordWrapEnabled(true)
            chart.legend.setTextSize(12f)
            chart.legend.setForm(Legend.LegendForm.CIRCLE)
            chart.centerText = "Rainfall in Saint Petersburg"

            val data = PieData(dataSet)
            chart.setData(data)
            chart.animateY(1200)
            chart.data.setValueTextSize(10f)
            chart.data.setValueTextColor(Color.WHITE)
            chart.invalidate()
        }
    }
}
