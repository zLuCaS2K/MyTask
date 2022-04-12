package com.zlucas2k.mytask.data.mappers

import com.zlucas2k.mytask.common.mapper.Mapper
import com.zlucas2k.mytask.data.entity.StatusDTO
import com.zlucas2k.mytask.domain.model.Status

object StatusDTOMapperImpl : Mapper<Status, StatusDTO> {

    override fun mapTo(modelEntry: Status): StatusDTO {
        return when (modelEntry) {
            Status.TODO -> StatusDTO.TODO
            Status.PROGRESS -> StatusDTO.PROGRESS
            Status.DONE -> StatusDTO.DONE
        }
    }

    override fun mapFrom(modelEntry: StatusDTO): Status {
        return when (modelEntry) {
            StatusDTO.TODO -> Status.TODO
            StatusDTO.PROGRESS -> Status.PROGRESS
            StatusDTO.DONE -> Status.DONE
        }
    }
}