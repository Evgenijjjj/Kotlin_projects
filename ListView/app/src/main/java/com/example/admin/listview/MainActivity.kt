package com.example.admin.listview

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lv = findViewById<ListView>(R.id.listView) as ListView
        val dataArray = Array<Int>( 101, { i-> i*i})
        val adapter = ArrayAdapter<Int>(this, android.R.layout.simple_list_item_1, dataArray)
        lv.adapter = adapter
        lv.setOnItemClickListener{
            parent, view, position, id ->
            var textView: TextView = view as TextView
            if (editText.text.isEmpty()) textView.text = "Clicked"
            else {
                textView.text = editText.text
            }
        }



    }

}
