package com.example.chainsearch

import android.util.Log
import com.example.chainsearch.initialAction.auth.RegisterUserFunctionality
import com.example.chainsearch.initialAction.auth.convertTo_Argon2id
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        RegisterUserFunctionality.registerEmail("greblaruee@yahoo.com", "123445", "eeee", null)
    }
}