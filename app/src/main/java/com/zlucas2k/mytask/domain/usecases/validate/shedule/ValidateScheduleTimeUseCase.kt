package com.zlucas2k.mytask.domain.usecases.validate.shedule

import com.zlucas2k.mytask.domain.model.Status

interface ValidateScheduleTimeUseCase {

    operator fun invoke(time: String, date: String, status: Status): Long
}