package com.example.admin.test

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.sqrt

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
fun stringLen(s: String): Int{
    var len = 0
    for(c in s)
        len++
    return len
}
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun correct(){
        assertEquals(42, sqrt(1764.0).toInt())
    }
    @Test
    fun math(){
        val dx: Double = 2.0
        assertEquals((1 + dx/2), sqrt(1 + dx), 0.5)
    }
    @Test
    fun strTest(){
        val s = "123456789"
        assertEquals(
                s.length,
                stringLen(s)
        )
    }
}


