package com.zlucas2k.mytask.presentation.common.model.mapper

import com.zlucas2k.mytask.common.mapper.Mapper
import com.zlucas2k.mytask.domain.model.Priority
import com.zlucas2k.mytask.presentation.common.model.PriorityView

object PriorityViewMapperImpl : Mapper<Priority, PriorityView> {

    override fun mapTo(modelEntry: Priority): PriorityView {
        return when (modelEntry) {
            Priority.NONE -> PriorityView.NONE
            Priority.LOW -> PriorityView.LOW
            Priority.MEDIUM -> PriorityView.MEDIUM
            Priority.HIGH -> PriorityView.HIGH
        }
    }

    override fun mapFrom(modelEntry: PriorityView): Priority {
        return when (modelEntry) {
            PriorityView.NONE -> Priority.NONE
            PriorityView.LOW -> Priority.LOW
            PriorityView.MEDIUM -> Priority.MEDIUM
            PriorityView.HIGH -> Priority.HIGH
        }
    }
}