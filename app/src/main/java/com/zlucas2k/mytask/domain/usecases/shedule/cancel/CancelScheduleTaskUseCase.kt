package com.zlucas2k.mytask.domain.usecases.shedule.cancel

import com.zlucas2k.mytask.domain.model.Task

interface CancelScheduleTaskUseCase {

    operator fun invoke(task: Task)
}