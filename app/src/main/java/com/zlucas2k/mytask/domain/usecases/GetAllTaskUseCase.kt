package com.zlucas2k.mytask.domain.usecases

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetAllTaskUseCase constructor(
    private val repository: TaskRepository
) {

    operator fun invoke(): Flow<List<Task>> {
        return repository.getAllTask()
    }
}