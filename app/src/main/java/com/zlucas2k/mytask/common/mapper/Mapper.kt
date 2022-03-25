package com.zlucas2k.mytask.common.mapper

interface Mapper<I, O> {

    fun mapTo(modelEntry: I): O

    fun mapFrom(modelEntry: O): I
}