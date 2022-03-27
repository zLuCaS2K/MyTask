package com.zlucas2k.mytask.domain.model

import java.util.Calendar
import com.zlucas2k.mytask.common.utils.Utils

data class Task(
    val id: Int = 0,
    val title: String,
    val date: String,
    val time: String,
    val description: String,
    val priority: Priority,
    val status: Status = Status.TODO
) {
    /**
     * Retorna true caso a data e a hora sejam válidas, caso seja uma data ou hora no passado
     * será retornado false.
     */
    fun isNotValidDateAndTimeTask(): Boolean {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
        }
        val dueTime = Utils.convertStringToCalendar(date, time)

        val diff = dueTime.timeInMillis - calendar.timeInMillis

        return diff < 0
    }
}