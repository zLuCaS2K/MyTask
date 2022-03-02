package com.zlucas2k.mytask

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.zlucas2k.mytask", appContext.packageName)
    }
}