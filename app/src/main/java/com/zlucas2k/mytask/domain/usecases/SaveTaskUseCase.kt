package com.zlucas2k.mytask.domain.usecases

import com.zlucas2k.mytask.common.exceptions.TaskException
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import javax.inject.Inject

class SaveTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(task: Task) {
        try {
            if (task.title.isEmpty() || task.description.isEmpty() || task.date.isEmpty()) {
                throw TaskException("Preencha Todos os Campos!")
            } else {
                repository.saveTask(task)
            }
        } catch (e: TaskException) {
            e.printStackTrace()
        }
    }
}