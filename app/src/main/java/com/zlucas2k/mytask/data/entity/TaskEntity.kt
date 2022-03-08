package com.zlucas2k.mytask.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zlucas2k.mytask.domain.model.Priority
import com.zlucas2k.mytask.domain.model.Status

@Entity(tableName = "Task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val date: String,
    val time: String,
    val description: String,
    val priority: Priority,
    val status: Status
)
