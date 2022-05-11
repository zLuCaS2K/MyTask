package com.zlucas2k.mytask.data.repository

import com.zlucas2k.mytask.data.database.TaskDAO
import com.zlucas2k.mytask.data.mappers.mapFromModel
import com.zlucas2k.mytask.domain.mappers.mapToEntity
import com.zlucas2k.mytask.domain.model.Status
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val _taskDAO: TaskDAO
) : TaskRepository {

    override fun getAllTask(): Flow<List<Task>> {
        return _taskDAO.getAllTask().map { tasksEntities ->
            tasksEntities.map { taskEntity ->
                taskEntity.mapFromModel()
            }
        }
    }

    override suspend fun getTaskById(id: Int): Task? {
        return _taskDAO.getTaskById(id)?.mapFromModel()
    }

    override suspend fun searchTask(query: String): Flow<List<Task>> {
        return _taskDAO.searchTask(query).map { tasksEntities ->
            tasksEntities.map { taskEntity ->
                taskEntity.mapFromModel()
            }
        }
    }

    override suspend fun filterTask(filter: Status): Flow<List<Task>> {
        val filterStatusDTO = filter.mapToEntity()
        return _taskDAO.filterTask(filterStatusDTO).map { tasksEntities ->
            tasksEntities.map { taskEntity ->
                taskEntity.mapFromModel()
            }
        }
    }

    override suspend fun saveTask(task: Task): Long {
        val taskEntity = task.mapToEntity()
        return _taskDAO.saveTask(taskEntity)
    }

    override suspend fun updateTask(task: Task) {
        val taskEntity = task.mapToEntity()
        _taskDAO.updateTask(taskEntity)
    }

    override suspend fun deleteTask(task: Task) {
        val taskEntity = task.mapToEntity()
        _taskDAO.deleteTask(taskEntity)
    }
}