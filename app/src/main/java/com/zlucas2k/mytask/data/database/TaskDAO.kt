package com.zlucas2k.mytask.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import com.zlucas2k.mytask.data.entity.StatusDTO
import com.zlucas2k.mytask.data.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {

    @Query("SELECT * FROM task")
    fun getAllTask(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskById(id: Int): TaskEntity?

    @Query("SELECT * FROM task WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchTask(query: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE status = :filter")
    fun filterTask(filter: StatusDTO): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTask(taskEntity: TaskEntity): Long

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)
}