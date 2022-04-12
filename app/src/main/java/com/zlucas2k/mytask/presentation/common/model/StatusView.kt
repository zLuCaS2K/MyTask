package com.zlucas2k.mytask.presentation.common.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.vector.ImageVector

enum class StatusView(val icon: ImageVector) {
    TODO(Icons.Filled.Schedule),
    PROGRESS(Icons.Filled.Work),
    DONE(Icons.Filled.Done)
}