package com.zlucas2k.mytask.infrastructure.worker.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.zlucas2k.mytask.common.utils.Constants
import com.zlucas2k.mytask.infrastructure.notification.NotificationUtils

class TaskWork(ctx: Context, workerParams: WorkerParameters) : Worker(ctx, workerParams) {

    override fun doWork(): Result {
        val taskData = inputData
        val taskTitle = taskData.getString(Constants.WORKER.KEYS.KEY_TITLE)
        val taskDescription = taskData.getString(Constants.WORKER.KEYS.KEY_DESCRIPTION)

        NotificationUtils.showNotification(applicationContext, taskTitle!!, taskDescription!!)

        return Result.success()
    }
}