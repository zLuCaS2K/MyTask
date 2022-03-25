package com.zlucas2k.mytask.presentation.common.model.mapper

import com.zlucas2k.mytask.common.mapper.Mapper
import com.zlucas2k.mytask.domain.model.Status
import com.zlucas2k.mytask.presentation.common.model.StatusView

object StatusViewMapperImpl : Mapper<Status, StatusView> {

    override fun mapTo(modelEntry: Status): StatusView {
        return when (modelEntry) {
            Status.TODO -> StatusView.TODO
            Status.DONE -> StatusView.DONE
            Status.PROGRESS -> StatusView.PROGRESS
        }
    }

    override fun mapFrom(modelEntry: StatusView): Status {
        return when (modelEntry) {
            StatusView.TODO -> Status.TODO
            StatusView.DONE -> Status.DONE
            StatusView.PROGRESS -> Status.PROGRESS
        }
    }
}