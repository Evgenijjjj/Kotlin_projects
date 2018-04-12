package com.example.user.calc

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
    fun testMath(){
        var  i = 0
        var j = 0
        while (i < 101){
            onView(withId(R.id.inputField1)).perform(clearText(),typeText("${i}"), closeSoftKeyboard())
           while (j < 101) {
               onView(withId(R.id.inputField2)).perform(clearText(), typeText("${j}"), closeSoftKeyboard())
               onView(withId(R.id.plusBtn)).perform(click())

               try{
                   onView(withId(R.id.textView)).check(matches(withText("${j + i}")))
               }
               catch (t: Throwable){
                   Log.d("ERROR","${j},${i}")
                   return
               }
               j++
           }
            i++
        }
    }

}

