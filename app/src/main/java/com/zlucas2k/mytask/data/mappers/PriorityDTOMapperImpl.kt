package com.zlucas2k.mytask.data.mappers

import com.zlucas2k.mytask.common.mapper.Mapper
import com.zlucas2k.mytask.data.entity.PriorityDTO
import com.zlucas2k.mytask.domain.model.Priority

object PriorityDTOMapperImpl : Mapper<Priority, PriorityDTO> {

    override fun mapTo(modelEntry: Priority): PriorityDTO {
        return when (modelEntry) {
            Priority.NONE -> PriorityDTO.NONE
            Priority.LOW -> PriorityDTO.LOW
            Priority.MEDIUM -> PriorityDTO.MEDIUM
            Priority.HIGH -> PriorityDTO.HIGH
        }
    }

    override fun mapFrom(modelEntry: PriorityDTO): Priority {
        return when (modelEntry) {
            PriorityDTO.NONE -> Priority.NONE
            PriorityDTO.LOW -> Priority.LOW
            PriorityDTO.MEDIUM -> Priority.MEDIUM
            PriorityDTO.HIGH -> Priority.HIGH
        }
    }
}