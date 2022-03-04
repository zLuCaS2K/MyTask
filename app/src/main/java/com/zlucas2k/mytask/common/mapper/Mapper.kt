package com.zlucas2k.mytask.common.mapper

interface Mapper<E, M> {

    fun mapEntityToModel(entity: E): M

    fun mapModelToEntity(model: M): E
}