package com.zlucas2k.mytask.domain.usecases.task.filter

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.util.TaskFilter
import kotlinx.coroutines.flow.Flow

interface FilterTaskUseCase {

    suspend operator fun invoke(filter: TaskFilter): Flow<List<Task>>
}