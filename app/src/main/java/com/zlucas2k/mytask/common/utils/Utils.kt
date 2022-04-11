package com.zlucas2k.mytask.common.utils

import java.util.Locale
import java.util.Calendar
import java.text.SimpleDateFormat

object Utils {

    private val dateFullFormatter = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.ROOT)

    fun convertStringToCalendar(date: String, time: String): Calendar {
        val calendar = Calendar.getInstance()
        val calendarNewDate = dateFullFormatter.parse("$date $time")
        calendar.time = calendarNewDate
        return calendar
    }
}