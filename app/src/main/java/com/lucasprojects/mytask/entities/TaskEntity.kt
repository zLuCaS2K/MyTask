package com.lucasprojects.mytask.entities

/** Classe entidade das Tasks */
data class TaskEntity(
    val id: Int,
    val userId: Int,
    var priorityId: Int,
    var description: String,
    var dueDate: String,
    var complete: Boolean = false
)