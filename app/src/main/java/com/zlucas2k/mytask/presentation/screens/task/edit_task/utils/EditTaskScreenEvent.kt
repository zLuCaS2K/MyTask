package com.zlucas2k.mytask.presentation.screens.task.edit_task.utils

sealed class EditTaskScreenEvent {
    object EditTaskSuccess : EditTaskScreenEvent()
    data class EditTaskFailed(val message: String) : EditTaskScreenEvent()
}
