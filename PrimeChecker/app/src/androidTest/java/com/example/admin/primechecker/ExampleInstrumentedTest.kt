package com.example.admin.primechecker

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testType(){
        onView(withId(R.id.editText)).perform(clearText(),typeText("-12-"), closeSoftKeyboard())
        onView(withId(R.id.checkBtn)).perform(click())
       try{
           onView(withId(R.id.textView)).check(matches(withText("error")))
       }
       catch(t: Throwable ){
           Log.d("TEST_TYPE_ERROR", "-12-")

           return
       }

        onView(withId(R.id.editText)).perform(clearText(),typeText("-+12"), closeSoftKeyboard())
        onView(withId(R.id.checkBtn)).perform(click())
        try{
            onView(withId(R.id.textView)).check(matches(withText("error")))
        }
        catch(t: Throwable ){
            Log.d("TEST_TYPE_ERROR", "-+12")

            return
        }
        onView(withId(R.id.editText)).perform(clearText(),typeText("-"), closeSoftKeyboard())
        onView(withId(R.id.checkBtn)).perform(click())
        try{
            onView(withId(R.id.textView)).check(matches(withText("prime")))
        }
        catch(t: Throwable ){
            Log.d("TEST_TYPE_ERROR", "-")

            return
        }
    }

    @Test
    fun clearTest(){
        onView(withId(R.id.editText)).perform(clearText(),typeText(""), closeSoftKeyboard())
        onView(withId(R.id.checkBtn)).perform(click())
        try {
            onView(withId(R.id.textView)).check(matches(withText("error")))
        }
        catch(t: Throwable ){
            Log.d("TEST_CLEAR_ERROR", "")

            return
        }
    }

    @Test
    fun testSimpleNumbers(){
        var  i =-2
        while (i < 8){
            Log.d("TAG", "${i}")
            onView(withId(R.id.editText)).perform(clearText(),typeText("${i}"), closeSoftKeyboard())
            onView(withId(R.id.checkBtn)).perform(click())

            try {
                onView(withId(R.id.textView)).check(matches(withText(findPrime(i.toString()))))
            }
            catch(t: Throwable ){
                Log.d("TEST_SIMPLE_NUMBERS_ERROR", "${i}")
                return
            }

        i++
        }
    }

    @Test
     fun testMaxInt() {
        onView(withId(R.id.editText)).perform(clearText(),typeText("${Int.MAX_VALUE}"), closeSoftKeyboard())
        onView(withId(R.id.checkBtn)).perform(click())
       try {
           onView(withId(R.id.textView)).check(matches(withText(findPrime(Int.MAX_VALUE.toString()))))
       }
        catch(t: Throwable ){
            Log.d("TEST_MAX_INT_ERROR", "${Int.MAX_VALUE}")
        }
    }
}
fun right_10(a:String): Boolean{
    if(a == "-")
        return false
    val int_type = Regex("^-?[0-9]*")
    if(( a.matches(int_type)) && a.toLong() <= Int.MAX_VALUE )
        return true
    return false
}
fun findPrime(s: String?): String{
    //Log.d("TAG", "${s}")
    if(s == null || s.length > 10 || !right_10(s)) {
        return "error"
    }
    var number = s.toInt()
    if(number < 2)
        return "not prime"
    var i = 2
    var q =0
    while(i < number){
        if(number % i == 0)
            q++
        i++
    }
    if(q == 0)
        return "prime"
    else
        return "not prime"
}



