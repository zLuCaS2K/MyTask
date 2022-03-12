package com.zlucas2k.mytask.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.zlucas2k.mytask.data.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {

    @Query("SELECT * FROM task")
    fun getAllTask(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskById(id: Int): TaskEntity?

    @Insert
    suspend fun saveTask(taskEntity: TaskEntity)

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)
}