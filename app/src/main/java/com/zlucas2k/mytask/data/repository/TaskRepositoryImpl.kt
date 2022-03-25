package com.zlucas2k.mytask.data.repository

import com.zlucas2k.mytask.common.mapper.TaskMapperImpl
import com.zlucas2k.mytask.data.database.TaskDAO
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val _taskDAO: TaskDAO
) : TaskRepository {

    override fun getAllTask(): Flow<List<Task>> {
        return _taskDAO.getAllTask().map { listTaskEntity ->
            listTaskEntity.map { taskEntity ->
                TaskMapperImpl.mapEntityToModel(taskEntity)
            }
        }
    }

    override suspend fun getTaskById(id: Int): Task? {
        return _taskDAO.getTaskById(id)?.let { TaskMapperImpl.mapEntityToModel(it) }
    }

    override suspend fun saveTask(task: Task): Long {
        val taskEntity = TaskMapperImpl.mapModelToEntity(task)
        return _taskDAO.saveTask(taskEntity)
    }

    override suspend fun deleteTask(task: Task) {
        val taskEntity = TaskMapperImpl.mapModelToEntity(task)
        _taskDAO.deleteTask(taskEntity)
    }
}