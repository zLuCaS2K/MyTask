package com.zlucas2k.mytask.domain.usecases.validate.shedule

import java.util.Calendar
import com.zlucas2k.mytask.common.utils.Utils
import com.zlucas2k.mytask.common.exceptions.TaskException
import com.zlucas2k.mytask.domain.model.Status

class ValidateScheduleTimeUseCaseImpl : ValidateScheduleTimeUseCase {

    override fun invoke(time: String, date: String, status: Status): Long {
        val nowTime = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
        }
        val dueTime = Utils.convertStringToCalendar(date, time)

        val timeInMillisTask = dueTime.timeInMillis - nowTime.timeInMillis

        if (status == Status.TODO) {
            if (timeInMillisTask <= 0) {
                throw TaskException("")
            }
        }

        return timeInMillisTask
    }
}