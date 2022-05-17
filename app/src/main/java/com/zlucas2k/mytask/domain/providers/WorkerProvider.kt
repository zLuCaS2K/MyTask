package com.zlucas2k.mytask.domain.providers

interface WorkerProvider<T> {

    fun createWork(data: T, delayInMillis: Long)

    fun cancelWork(data: T)
}