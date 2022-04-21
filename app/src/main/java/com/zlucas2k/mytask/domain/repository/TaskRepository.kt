package com.zlucas2k.mytask.domain.repository

import com.zlucas2k.mytask.domain.model.Status
import com.zlucas2k.mytask.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getAllTask(): Flow<List<Task>>

    suspend fun getTaskById(id: Int): Task?

    suspend fun searchTask(query: String): Flow<List<Task>>

    suspend fun filterTask(filter: Status): Flow<List<Task>>

    suspend fun saveTask(task: Task): Long

    suspend fun deleteTask(task: Task)
}