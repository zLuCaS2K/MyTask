package com.zlucas2k.mytask.domain.usecases

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository

class SaveTaskUseCase constructor(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(task: Task) {
        // TODO: Implementar Validações
        repository.saveTask(task)
    }
}