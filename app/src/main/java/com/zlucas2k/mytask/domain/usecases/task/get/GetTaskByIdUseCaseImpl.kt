package com.zlucas2k.mytask.domain.usecases.task.get

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskByIdUseCaseImpl @Inject constructor(
    private val repository: TaskRepository
) : GetTaskByIdUseCase {

    override suspend fun invoke(id: Int): Task? {
        return repository.getTaskById(id)
    }
}