package com.zlucas2k.mytask.data.repository

import com.zlucas2k.mytask.data.database.TaskDAO
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val taskDAO: TaskDAO
) : TaskRepository {

    override fun getAllTask(): Flow<List<Task>> {
        TODO("Implementar Listagem")
    }

    override suspend fun saveTask(task: Task) {
        TODO("Implementar Inserção")
    }

    override suspend fun deleteTask(task: Task) {
        TODO("Implementar Exclução")
    }
}