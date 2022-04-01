package com.zlucas2k.mytask.domain.usecases.task.save

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import javax.inject.Inject

class SaveTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository
) : SaveTaskUseCase {

    override suspend operator fun invoke(task: Task): Long {
        return repository.saveTask(task)
    }
}