package com.example.admin.calc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //проверка 16 го числа
        fun right_16(a:String, b:String):Boolean {
            val type_16 = Regex("[0-9a-f]*")
            if(a.matches(type_16) && b.matches(type_16) && a.toLong(radix = 16) <= Int.MAX_VALUE && b.toLong(radix = 16) <= Int.MAX_VALUE)
                return true
            textView.text = "Wrong type !"
            return false
        }
        // проверка 10 го числа
        fun right_10(a:String, b:String): Boolean{
            val int_type = Regex("[0-9]*")
            if(a.matches(int_type) && b.matches(int_type) && a.toLong() <= Int.MAX_VALUE && b.toLong() <= Int.MAX_VALUE)
                return true
            textView.text = "Wrong type !"
            return false
        }

        var modFlag: Boolean = false
        toggleButton.setOnCheckedChangeListener{_,isChecked ->
            if(isChecked){
                modFlag = true
            }else{
                modFlag = false
            }
        }
        plusButton.setOnClickListener(){
            if(arg1.text.isEmpty() || arg2.text.isEmpty()){
                textView.text = "Fields are empty!"
                return@setOnClickListener
            }
            if(modFlag){
                if(!right_16(arg1.text.toString(),arg2.text.toString()))return@setOnClickListener
                var res = arg1.text.toString().toLong(radix = 16) + arg2.text.toString().toLong(radix = 16)
                textView.text = "${res.toString(radix = 16)}"
            }
            else {
                if(!right_10(arg1.text.toString(), arg2.text.toString()))return@setOnClickListener
                var res = arg1.text.toString().toInt() + arg2.text.toString().toInt()
                textView.text = "${res}"
            }
        }


        minusButton.setOnClickListener() {
            if(arg1.text.isEmpty() || arg2.text.isEmpty()){
                textView.text = "Fields are empty!"
                return@setOnClickListener
            }
            if (modFlag) {
                if (!right_16(arg1.text.toString(), arg2.text.toString())) return@setOnClickListener
                var res = arg1.text.toString().toLong(radix = 16) - arg2.text.toString().toLong(radix = 16)
                textView.text = "${res.toString(radix = 16)}"
            } else {
                if(!right_10(arg1.text.toString(), arg2.text.toString()))return@setOnClickListener
                var res = arg1.text.toString().toInt() - arg2.text.toString().toInt()
                textView.text = "${res}"
            }
        }


        multiplyButton.setOnClickListener() {
            if(arg1.text.isEmpty() || arg2.text.isEmpty()){
                textView.text = "Fields are empty!"
                return@setOnClickListener
            }
            if (modFlag) {
                if (!right_16(arg1.text.toString(), arg2.text.toString())) return@setOnClickListener
                var res = arg1.text.toString().toLong(radix = 16) * arg2.text.toString().toLong(radix = 16)
                textView.text = "${res.toString(radix = 16)}"
            } else {
                if(!right_10(arg1.text.toString(), arg2.text.toString()))return@setOnClickListener
                var res = arg1.text.toString().toInt() * arg2.text.toString().toInt()
                textView.text = "${res}"
            }
        }

        divisionButton.setOnClickListener(){
            if(arg1.text.isEmpty() || arg2.text.isEmpty()){
                textView.text = "Fields are empty!"
                return@setOnClickListener
            }
            if(modFlag){
                if(!right_16(arg1.text.toString(),arg2.text.toString()))return@setOnClickListener
                if(arg2.text.toString().toLong(radix = 16).toInt() == 0) {
                    textView.text = "division by zero!"
                    return@setOnClickListener
                }
                var res = arg1.text.toString().toLong(radix = 16) / arg2.text.toString().toLong(radix = 16)
                textView.text = "${res.toString(radix = 16)}"
            }
            else {
                if(!right_10(arg1.text.toString(), arg2.text.toString()))return@setOnClickListener
                if(arg2.text.toString().toInt() == 0) {
                    textView.text = "division by zero!"
                    return@setOnClickListener
                }
                if(!right_10(arg1.text.toString(), arg2.text.toString()))return@setOnClickListener
                var res = arg1.text.toString().toDouble() / arg2.text.toString().toDouble()
                textView.text = "${res}"
            }
        }
    }
}
