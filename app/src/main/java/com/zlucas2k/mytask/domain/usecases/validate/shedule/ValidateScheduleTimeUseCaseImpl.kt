package com.zlucas2k.mytask.domain.usecases.validate.shedule

import com.zlucas2k.mytask.R
import java.util.Calendar
import com.zlucas2k.mytask.common.utils.Utils
import com.zlucas2k.mytask.common.exceptions.TaskException
import com.zlucas2k.mytask.domain.model.Status
import com.zlucas2k.mytask.domain.providers.StringResourceProvider
import javax.inject.Inject

class ValidateScheduleTimeUseCaseImpl @Inject constructor(
    private val stringResourceProvider: StringResourceProvider
) : ValidateScheduleTimeUseCase {

    override fun invoke(time: String, date: String, status: Status): Long {
        val nowTime = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
        }
        val dueTime = Utils.convertStringToCalendar(date, time)

        val timeInMillisTask = dueTime.timeInMillis - nowTime.timeInMillis

        if (status == Status.TODO) {
            if (timeInMillisTask <= 0) {
                throw TaskException(stringResourceProvider.getString(R.string.enter_time_and_date_valid))
            }
        }

        return timeInMillisTask
    }
}