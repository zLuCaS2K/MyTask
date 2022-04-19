package com.zlucas2k.mytask.domain.usecases.shedule.shedule

import com.zlucas2k.mytask.domain.model.Task

interface ScheduleTaskUseCase {

    operator fun invoke(task: Task, delayInMillis: Long)
}