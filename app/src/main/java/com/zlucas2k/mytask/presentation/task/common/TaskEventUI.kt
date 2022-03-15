package com.zlucas2k.mytask.presentation.task.common

sealed class TaskEventUI {
    object SaveTask : TaskEventUI()
    object DeleteTask : TaskEventUI()
    data class ShowToast(val message: String) : TaskEventUI()
}
