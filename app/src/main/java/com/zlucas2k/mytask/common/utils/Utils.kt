package com.zlucas2k.mytask.common.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    private val timeFormatter = SimpleDateFormat("hh:mm", Locale.ROOT)
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
    private val dateFullFormatter = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.ROOT)

    fun formatTime(time: String): String {
        timeFormatter.timeZone = TimeZone.getDefault()
        return timeFormatter.format(timeFormatter.parse(time))
    }

    fun formatDate(date: String): String {
        return dateFormatter.format(dateFormatter.parse(date))
    }

    fun convertStringToCalendar(date: String, time: String): Calendar {
        val calendar = Calendar.getInstance()
        val calendarNewDate = dateFullFormatter.parse("$date $time")
        calendar.time = calendarNewDate
        return calendar
    }
}