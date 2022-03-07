package com.zlucas2k.mytask.domain.model

import androidx.compose.ui.graphics.Color
import com.zlucas2k.mytask.presentation.common.theme.HighPriorityColor
import com.zlucas2k.mytask.presentation.common.theme.LowPriorityColor
import com.zlucas2k.mytask.presentation.common.theme.MediumPriorityColor
import com.zlucas2k.mytask.presentation.common.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}