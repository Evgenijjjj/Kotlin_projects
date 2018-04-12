package com.example.admin.primechecker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    fun right_10(a:String): Boolean{
        if(a == "-")
            return false
        val int_type = Regex("^-?[0-9]*")
        if((a.matches(int_type)) && a.toLong() <= Int.MAX_VALUE )
            return true
        return false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkBtn.setOnClickListener(){
            if(editText.text.isEmpty() || editText.text.toString().length > 10 || !right_10(editText.text.toString())) {
                textView.text = "error"
                return@setOnClickListener
            }
            val number = editText.text.toString().toInt()
            if (number < 2){
                textView.text = "not prime"
                return@setOnClickListener
            }

            var i = 2
            var q =0
            while(i < number){
                if(number % i == 0)
                    q++
                i++
            }
            if(q == 0)
                textView.text = "prime"
            else
                textView.text = "not prime"
            return@setOnClickListener
        }
    }
}
