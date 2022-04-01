package com.zlucas2k.mytask.infrastructure.worker.provider

interface WorkerProvider<T> {

    fun createWork(data: T, delayInMillis: Long = 0)

    fun cancelWork(data: T)
}