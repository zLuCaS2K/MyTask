package com.zlucas2k.mytask.presentation.task

import android.content.res.Configuration
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.domain.model.Priority
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.home.HomeViewModel
import com.zlucas2k.mytask.presentation.task.components.TaskScreenContent

@Composable
fun TaskScreen(
    selectedTask: Task?,
    homeViewModel: HomeViewModel
) {
    val title: String by homeViewModel.titleTask
    val description: String by homeViewModel.descriptionTask
    val priority: Priority by homeViewModel.priorityTask
    val date: String by homeViewModel.dateTask
    val completed: Boolean by homeViewModel.completedTask

    Scaffold(
        topBar = {

        },
        content = {
            TaskScreenContent(
                title = title,
                onTitleChange = {
                    homeViewModel.titleTask.value = it
                },
                description = description,
                onDescriptionChange = {
                    homeViewModel.descriptionTask.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    homeViewModel.priorityTask.value = it
                },
                date = date,
                onDateChange = {
                    homeViewModel.dateTask.value = it
                },
                completed = completed,
                onCompletedChange = {
                    homeViewModel.completedTask.value = it
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