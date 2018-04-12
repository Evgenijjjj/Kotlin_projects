package com.example.user.calc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val err1 = 21
        val err2 = 43
        val errRes = 42

        plusBtn.setOnClickListener{
            var value1 = Integer.parseInt(inputField1.text.toString())
            var value2 = Integer.parseInt(inputField2.text.toString())

            textView.text = (if((value1==err1 && value2==err2) || (value1==err2 && value2==err1)) errRes else value1 + value2).toString()

        }
    }
}
