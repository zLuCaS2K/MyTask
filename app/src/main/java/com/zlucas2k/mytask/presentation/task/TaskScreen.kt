package com.zlucas2k.mytask.presentation.task

import android.content.res.Configuration
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.domain.model.Priority
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.viewmodel.TaskViewModel
import com.zlucas2k.mytask.presentation.task.components.TaskScreenContent

@Composable
fun TaskScreen(
    selectedTask: Task?,
    taskViewModel: TaskViewModel
) {
    val title: String by taskViewModel.titleTask
    val description: String by taskViewModel.descriptionTask
    val priority: Priority by taskViewModel.priorityTask
    val date: String by taskViewModel.dateTask
    val completed: Boolean by taskViewModel.completedTask

    Scaffold(
        topBar = {

        },
        content = {
            TaskScreenContent(
                title = title,
                onTitleChange = {
                    taskViewModel.titleTask.value = it
                },
                description = description,
                onDescriptionChange = {
                    taskViewModel.descriptionTask.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    taskViewModel.priorityTask.value = it
                },
                date = date,
                onDateChange = {
                    taskViewModel.dateTask.value = it
                },
                completed = completed,
                onCompletedChange = {
                    taskViewModel.completedTask.value = it
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