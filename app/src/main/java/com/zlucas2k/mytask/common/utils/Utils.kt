package com.zlucas2k.mytask.common.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
    private val dateFullFormatter = SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.ROOT)

    fun formatTime(hour: Int, minute: Int) = String.format("%02d:%02d", hour, minute)

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