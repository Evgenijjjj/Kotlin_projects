package com.example.admin.convertapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun right_float(a:String): Boolean{
           try{
               a.toFloat()
               return true
           }
           catch (t: Throwable){
               return false
           }
        }

        var modFlag: Boolean = true
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                    modFlag = false
                textView.text = "kilometers/inches"
                if(editText.text != null && right_float(editText.text.toString())){
                editText2.setText("${(editText.text.toString().toFloat() * 39370.1).toFloat()}")}
                    return@setOnCheckedChangeListener
                }
            else{
                modFlag = true
                textView.text = "seconds/hours"
                if(editText.text != null && right_float(editText.text.toString())){
                editText2.setText("${(editText.text.toString().toFloat() /3600).toFloat()}")}
                return@setOnCheckedChangeListener
            }
        }
       /* fun equalBoxes():Boolean{
            if(editText.text.isEmpty() || editText2.text.isEmpty())
                return false
            var r1: Float
            var r2: Float
            try {
                r1 = editText.text.toString().toFloat()
                r2 = editText2.text.toString().toFloat()
            }
            catch (e: NumberFormatException){
                return false
            }
            if(abs(r1  - r2/39370.1) < 0.0000001) {
                Log.d("ERROR_TRUE","r1: ${r1} r2: ${r2} delta r1r2: ${abs(r1  - r2*39370.1)}")
                return true
            }
            if(abs(r1 - r2*3600) < 0.00000001){
                Log.d("ERROR_TRUE","r1: ${r1} r2: ${r2} delta r1r2: ${abs(r1 - r2/3600)}")
                return true
            }
            Log.d("ERROR_FALSE","r1: ${r1} r2: ${r2} delta r1r2: ${abs(r1 - r2)}")
            return false
        }*/
        var _ignore: Boolean = false
        editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                    if(_ignore)
                        return
                    var s = editText.text
                    if (s == null || !right_float(s.toString())) {
                        editText2.setText("error")
                        return
                    }

                    if (!modFlag) {
                        editText2.setText("${(s.toString().toFloat() * 39370.1).toFloat()}")
                    }
                    if (modFlag) {
                        editText2.setText("${(s.toString().toFloat() / 3600).toFloat()}")
                    }

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
               /* if(equalBoxes()){
                    _ignore = true
                    return
                }

                _ignore = false*/
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


        editText2.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (!_ignore)
                    return
                    var s = editText2.text
                    if (s == null || !right_float(s.toString())) {
                        editText.setText("error")
                        return
                    }

                    if (!modFlag) {
                        editText.setText("${(s.toString().toFloat() / 39370.1).toFloat()}")
                    }
                    if (modFlag) {
                        editText.setText("${(s.toString().toFloat() * 3600).toFloat()}")
                    }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
              /*  if(equalBoxes()){
                    _ignore = true
                    return
                }

                _ignore = false
*/

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

    }
}
