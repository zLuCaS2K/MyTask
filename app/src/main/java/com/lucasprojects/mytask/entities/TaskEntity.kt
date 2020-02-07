package com.lucasprojects.mytask.entities

data class TaskEntity(
    val id: Int,
    val userId: Int,
    var priorityId: Int,
    var description: String,
    var dueDate: String,
    var complete: Boolean = false
)