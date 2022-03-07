package com.zlucas2k.mytask.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zlucas2k.mytask.domain.model.Priority

@Entity(tableName = "Task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority,
    val date: String,
    val isCompleted: Boolean
)
