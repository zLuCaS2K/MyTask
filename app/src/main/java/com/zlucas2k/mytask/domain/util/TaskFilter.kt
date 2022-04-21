package com.zlucas2k.mytask.domain.util

sealed class TaskFilter {
    object All : TaskFilter()
    object Todo : TaskFilter()
    object Progress : TaskFilter()
    object Done : TaskFilter()
}
