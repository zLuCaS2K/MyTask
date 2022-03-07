package com.zlucas2k.mytask.domain.model

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority,
    val date: String,
    val isCompleted: Boolean
)
