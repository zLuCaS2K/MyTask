package com.zlucas2k.mytask.data.repository

import com.zlucas2k.mytask.common.mapper.TaskMapperImpl
import com.zlucas2k.mytask.data.database.TaskDAO
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val taskDAO: TaskDAO
) : TaskRepository {

    override fun getAllTask(): Flow<List<Task>> {
        return taskDAO.getAllTask().map { listTaskEntity ->
            listTaskEntity.map { taskEntity ->
                TaskMapperImpl.mapEntityToModel(taskEntity)
            }
        }
    }

    override suspend fun saveTask(task: Task) {
        val taskEntity = TaskMapperImpl.mapModelToEntity(task)
        taskDAO.saveTask(taskEntity)
    }

    override suspend fun deleteTask(task: Task) {
        val taskEntity = TaskMapperImpl.mapModelToEntity(task)
        taskDAO.saveTask(taskEntity)
    }
}