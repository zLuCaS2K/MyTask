package com.zlucas2k.mytask

import com.zlucas2k.mytask.common.utils.Utils
import org.junit.Assert
import org.junit.Test
import java.util.*

class UtilsTest {

    @Test
    fun `should format the time to the pattern hh mm with zero correction`() {
        val time = "8:2"

        val timeFormatted = Utils.formatTime(time)

        Assert.assertEquals("08:02", timeFormatted)
    }

    @Test
    fun `should format the date to the pattern dd MM yyyy with zero correction`() {
        val date = "20/2/2022"

        val dateFormatted = Utils.formatDate(date)

        Assert.assertEquals("20/02/2022", dateFormatted)
    }

    @Test
    fun `should convert the date and time into a calendar object`() {
        val date = "20/2/2022"
        val time = "8:20"

        val calendar = Utils.convertStringToCalendar(date, time)

        Assert.assertEquals(20, calendar.get(Calendar.DAY_OF_MONTH))
        Assert.assertEquals(2, calendar.get(Calendar.MONTH) + 1)
        Assert.assertEquals(2022, calendar.get(Calendar.YEAR))

        Assert.assertEquals(8, calendar.get(Calendar.HOUR))
        Assert.assertEquals(20, calendar.get(Calendar.MINUTE))
    }
}