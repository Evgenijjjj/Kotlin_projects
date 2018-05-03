package com.example.admin.convertapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun right_float(a:String?): Boolean{
           try{
               a.toString().toFloat()
               if(a.toString().toFloat() < 0.0)
                   return false
               return true
           }
           catch (e: NumberFormatException){
               return false
           }
        }

        fun Equal(a: String?, b: String?):Boolean{
            try{
                if(abs(a.toString().toFloat() - b.toString().toFloat()*39370) < 0.0001)
                    return true
            }
            catch (e: NumberFormatException){
                return false
            }
            return false
        }

        editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                Log.d("CHECK_EQUAL_L","1: ${editText.text}, 2: ${editText2.text}, EQUAL: ${Equal(editText.text.toString(),editText2.text.toString())}")
                val a = editText.text.toString()
                if(a == "error")
                    return
                if(!right_float(a)) {
                    editText2.setText("error")
                    return
                }
                if(right_float(editText2.text.toString()) && Equal(a,editText2.text.toString())){
                    return
                }
                editText2.setText("${a.toFloat()/39370}")
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


        editText2.addTextChangedListener(object : TextWatcher{

            override fun afterTextChanged(s: Editable?) {

                Log.d("CHECK_EQUAL_R","1: ${editText.text}, 2: ${editText2.text}, EQUAL: ${Equal(editText.text.toString(),editText2.text.toString())}")
                val a = editText2.text.toString()
                if(a == "error")
                    return
                if(!right_float(a)) {
                    editText.setText("error")
                    return
                }
                if(right_float(editText.text.toString()) && Equal(editText.text.toString(),a)){
                    return
                }
                editText.setText("${a.toFloat()*39370}")
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

    }
}
