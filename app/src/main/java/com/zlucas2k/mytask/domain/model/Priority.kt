package com.zlucas2k.mytask.domain.model

import androidx.compose.ui.graphics.Color
import com.zlucas2k.mytask.presentation.common.theme.*

enum class Priority(val colorLight: Color, val colorDark: Color) {
    HIGH(HighPriorityColor700, HighPriorityColor200),
    MEDIUM(MediumPriorityColor700, MediumPriorityColor200),
    LOW(LowPriorityColor700, LowPriorityColor200),
    NONE(NonePriorityColor700, NonePriorityColor200)
}