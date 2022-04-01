package com.zlucas2k.mytask.domain.usecases.task.get_all

import com.zlucas2k.mytask.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface GetAllTaskUseCase {

    suspend operator fun invoke(): Flow<List<Task>>
}