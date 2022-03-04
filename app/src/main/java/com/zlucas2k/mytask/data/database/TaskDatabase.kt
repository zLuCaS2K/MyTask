package com.zlucas2k.mytask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zlucas2k.mytask.data.entity.TaskEntity

@Database(entities = [TaskEntity::class], exportSchema = false, version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract val taskDAO: TaskDAO
}