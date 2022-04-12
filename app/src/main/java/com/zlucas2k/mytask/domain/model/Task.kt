package com.zlucas2k.mytask.domain.model

import com.zlucas2k.mytask.common.utils.Utils
import java.util.Calendar

data class Task(
    val id: Int = 0,
    val title: String,
    val date: String,
    val time: String,
    val description: String,
    val priority: Priority,
    val status: Status
) {
    /**
     * Retorna o tempo de atraso da tarefa em milisegundos.
     */
    fun getScheduleDelayInMillis(): Long {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
        }
        val dueTime = Utils.convertStringToCalendar(date, time)

        return dueTime.timeInMillis - calendar.timeInMillis
    }
}