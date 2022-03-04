package com.zlucas2k.mytask.domain.repository

import com.zlucas2k.mytask.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getAllTask(): Flow<List<Task>>

    suspend fun saveTask(task: Task)

    suspend fun deleteTask(task: Task)
}