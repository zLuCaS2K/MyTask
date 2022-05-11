package com.zlucas2k.mytask.presentation.screens.task.add_task.utils

sealed class AddTaskScreenEvent {
    object SaveTaskSuccess : AddTaskScreenEvent()
    data class SaveTaskFailed(val message: String) : AddTaskScreenEvent()
}
