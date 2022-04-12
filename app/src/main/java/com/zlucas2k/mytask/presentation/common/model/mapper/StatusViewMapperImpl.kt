package com.zlucas2k.mytask.presentation.common.model.mapper

import com.zlucas2k.mytask.common.mapper.Mapper
import com.zlucas2k.mytask.domain.model.Status
import com.zlucas2k.mytask.presentation.common.model.StatusView

object StatusViewMapperImpl : Mapper<Status, StatusView> {

    override fun mapTo(modelEntry: Status): StatusView {
        return when (modelEntry) {
            Status.TODO -> StatusView.TODO
            Status.PROGRESS -> StatusView.PROGRESS
            Status.DONE -> StatusView.DONE
        }
    }

    override fun mapFrom(modelEntry: StatusView): Status {
        return when (modelEntry) {
            StatusView.TODO -> Status.TODO
            StatusView.PROGRESS -> Status.PROGRESS
            StatusView.DONE -> Status.DONE
        }
    }
}