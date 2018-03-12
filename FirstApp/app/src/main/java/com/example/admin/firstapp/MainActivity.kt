package com.example.admin.firstapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.admin.firstapp.R.id.result_label
import kotlinx.android.synthetic.main.activity_main.*

fun isDigit(a:String, b: String): Boolean{
    val int_type = Regex("[0-9]*")
    if (a.matches(int_type) && b.matches(int_type))
        return true
    else {
        return false
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun searchButtonPushed(view: View){
        result_label.text = ""

        if(first_number.text.isNotEmpty() && second_number.text.isNotEmpty()){
            val a = first_number.text.toString()
            val b = second_number.text.toString()


            if(!isDigit(a,b)) {
                result_label.text = "The program works only with positive integers!"
                return
            }
            var ia = a.toInt()
            var ib = b.toInt()
            if(ia > ib){
                result_label.text = "Range set incorrectly."
                return
            }
            if (ib < 2 ){
                result_label.text = "In this range, there are no prime numbers."
                return
            }
            if(ia < 2){
                ia = 2
            }
            var k: Int
            var q: Int
            while (ia != ib+1){
                k=0
                q=2
                while(q < ia){
                    if(ia % q == 0){
                        k++
                    }
                    q++
                }
                if(k==0) {
                    result_label.text = "${result_label.text} $ia"
                }
                ia++
            }
            if(result_label.text == "")
                result_label.text = "In this range, there are no prime numbers."

        }
        else{
            val test = Toast.makeText(this, "fields are empty", Toast.LENGTH_SHORT)
            test.show()
            result_label.text = ""
            return
        }

    }
}
