package com.zlucas2k.mytask.infrastructure.worker.provider

import android.content.Context
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.zlucas2k.mytask.common.utils.Constants
import com.zlucas2k.mytask.common.utils.Utils
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.infrastructure.worker.work.TaskWork
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TaskWorkerProviderImpl @Inject constructor(
    context: Context
) : TaskWorkerProvider {

    private val _workManager = WorkManager.getInstance(context)
    private val _workConstraints = Constraints.Builder().build()

    override fun createTaskWork(task: Task) {
        val currentDate = Calendar.getInstance()
        val taskDueDate = Utils.convertStringToCalendar(task.date, task.time)

        val diffInMillis = taskDueDate.timeInMillis - currentDate.timeInMillis
        val diffInSeconds = diffInMillis / 1000

        val taskData = Data.Builder()
            .putString(Constants.WORKER.KEYS.KEY_TITLE, task.title)
            .putString(Constants.WORKER.KEYS.KEY_DESCRIPTION, task.description)
            .build()

        val taskWorkRequest = OneTimeWorkRequestBuilder<TaskWork>()
            .setInitialDelay(diffInSeconds, TimeUnit.SECONDS)
            .setConstraints(_workConstraints)
            .setInputData(taskData)
            .addTag("TASK_WORKER_N${task.id}")
            .build()

        _workManager.enqueue(taskWorkRequest)
    }

    override fun cancelTaskWork(task: Task) {
        _workManager.cancelAllWorkByTag("TASK_WORKER_N${task.id}")
    }
}