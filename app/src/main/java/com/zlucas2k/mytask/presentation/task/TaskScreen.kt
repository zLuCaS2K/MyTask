package com.zlucas2k.mytask.presentation.task

import android.content.res.Configuration
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.domain.model.Priority
import com.zlucas2k.mytask.domain.model.Status
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.viewmodel.TaskViewModel

@Composable
fun TaskScreen(
    selectedTask: Task?,
    taskViewModel: TaskViewModel
) {
    val title: String by taskViewModel.titleTask
    val date: String by taskViewModel.dateTask
    val time: String by taskViewModel.timeTask
    val description: String by taskViewModel.descriptionTask
    val priority: Priority by taskViewModel.priorityTask
    val status: Status by taskViewModel.statusTask

    Scaffold(
        topBar = {

        },
        content = {
            TaskScreenContent(
                title = title,
                onTitleChange = {
                    taskViewModel.titleTask.value = it
                },
                date = date,
                onDateChange = {
                    taskViewModel.dateTask.value = it
                },
                time = time,
                onTimeChange = {
                    taskViewModel.timeTask.value = it
                },
                description = description,
                onDescriptionChange = {
                    taskViewModel.descriptionTask.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    taskViewModel.priorityTask.value = it
                },
                status = status,
                onStatusChange = {
                    taskViewModel.statusTask.value = it
                }
            )
        }
    )
}


@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {

    }
}