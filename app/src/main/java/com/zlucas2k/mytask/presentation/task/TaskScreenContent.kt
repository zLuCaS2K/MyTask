package com.zlucas2k.mytask.presentation.task

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.domain.model.Priority
import com.zlucas2k.mytask.domain.model.Status
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.task.components.TaskDatePicker
import com.zlucas2k.mytask.presentation.task.components.TaskPriorityDropDown
import com.zlucas2k.mytask.presentation.task.components.TaskTextField
import com.zlucas2k.mytask.presentation.task.components.TaskTimePicker

@Composable
fun TaskScreenContent(
    title: String,
    onTitleChange: (String) -> Unit,
    date: String,
    onDateChange: (String) -> Unit,
    time: String,
    onTimeChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit,
    status: Status,
    onStatusChange: (Status) -> Unit
) {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TaskTextField(
            value = title,
            onValueChange = { onTitleChange(it) },
            placeholderText = "Título",
            singleLine = true,
            maxLines = 1,
            modifier = modifier,
        )

        TaskPriorityDropDown(
            priority = priority,
            onPrioritySelected = onPrioritySelected,
            modifier = modifier
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TaskDatePicker(
                value = date,
                onValueChange = { onDateChange(it) },
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f),
            )

            TaskTimePicker(
                value = time,
                onValueChange = { onTimeChange(it) },
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f),
            )
        }

        TaskTextField(
            value = description,
            onValueChange = { onDescriptionChange(it) },
            placeholderText = "Descrição",
            singleLine = false,
            maxLines = 20,
            modifier = modifier
        )
    }
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        val title = "Titulo"
        val date = "Data"
        val time = "Hora"
        val description = "It is a long established fact that a reader will be distracted by the " +
                "readable content of a page when looking at its layout. The point of using Lorem Ipsum" +
                " is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here," +
                " content here', making it look like readable English."
        val priority = Priority.HIGH
        val status = Status.TODO

        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            TaskScreenContent(
                title = title,
                onTitleChange = {},
                date = date,
                onDateChange = {},
                time = time,
                onTimeChange = {},
                description = description,
                onDescriptionChange = {},
                priority = priority,
                onPrioritySelected = {},
                status = status,
                onStatusChange = {}
            )
        }
    }
}