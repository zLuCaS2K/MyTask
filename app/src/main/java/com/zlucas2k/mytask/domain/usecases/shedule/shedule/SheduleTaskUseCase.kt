package com.zlucas2k.mytask.domain.usecases.shedule.shedule

import com.zlucas2k.mytask.domain.model.Task

interface SheduleTaskUseCase {

    operator fun invoke(task: Task, delayInMillis: Long)
}