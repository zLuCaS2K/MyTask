package com.zlucas2k.mytask.domain.usecases

import com.zlucas2k.mytask.common.exceptions.TaskException
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import javax.inject.Inject

class SaveTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    // TODO: Resolver problema de internacionalização dessas strings.

    @Throws(TaskException::class)
    suspend operator fun invoke(task: Task) {
        if (task.title.isBlank()) {
            throw TaskException("Insira um título")
        }
        if (task.description.isBlank()) {
            throw TaskException("Insira uma descrição")
        }
        repository.saveTask(task)
    }
}