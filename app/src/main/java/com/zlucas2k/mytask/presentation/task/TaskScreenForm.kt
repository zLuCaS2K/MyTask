package com.zlucas2k.mytask.presentation.task

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.model.PriorityView
import com.zlucas2k.mytask.presentation.common.model.StatusView
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.task.components.*
import com.zlucas2k.mytask.presentation.task.components.dropdowns.PriorityDropDownMenu
import com.zlucas2k.mytask.presentation.task.components.dropdowns.StatusDropDownMenu
import com.zlucas2k.mytask.presentation.task.components.pickers.DatePicker
import com.zlucas2k.mytask.presentation.task.components.pickers.TimePicker

@Composable
fun TaskScreenForm(
    isEditing: Boolean,
    title: String,
    onTitleChange: (String) -> Unit,
    date: String,
    onDateChange: (String) -> Unit,
    time: String,
    onTimeChange: (Int, Int) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: PriorityView,
    onPrioritySelected: (PriorityView) -> Unit,
    status: StatusView,
    onStatusSelected: (StatusView) -> Unit
) {
    val modifier = Modifier.fillMaxWidth()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TaskTextField(
            value = title,
            onValueChange = { onTitleChange(it) },
            textStyle = MaterialTheme.typography.h1,
            placeholderText = stringResource(id = R.string.title_task),
            singleLine = true,
            maxLines = 1,
            modifier = modifier,
        )

        Row {
            PriorityDropDownMenu(
                priority = priority,
                onPrioritySelected = onPrioritySelected,
                modifier = modifier
                    .weight(1f)
                    .padding(start = 20.dp, top = 10.dp, end = 10.dp)
            )

            if (isEditing) {
                StatusDropDownMenu(
                    status = status,
                    onStatusSelected = onStatusSelected,
                    modifier = modifier
                        .weight(1f)
                        .padding(start = 10.dp, top = 10.dp, end = 20.dp)
                )
            }
        }

        TimePicker(
            value = time,
            onValueChange = { hour, minute ->
                onTimeChange(hour, minute)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp, end = 10.dp)
        )

        DatePicker(
            value = date,
            onValueChange = { onDateChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp, end = 10.dp)
        )

        TaskTextField(
            value = description,
            onValueChange = { onDescriptionChange(it) },
            textStyle = MaterialTheme.typography.body2,
            placeholderText = stringResource(id = R.string.description),
            singleLine = false,
            maxLines = 20,
            modifier = modifier.fillMaxHeight()
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
        val priority = PriorityView.HIGH
        val status = StatusView.TODO

        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            TaskScreenForm(
                isEditing = true,
                title = title,
                onTitleChange = {},
                date = date,
                onDateChange = {},
                time = time,
                onTimeChange = { _, _ ->

                },
                description = description,
                onDescriptionChange = {},
                priority = priority,
                onPrioritySelected = {},
                status = status,
                onStatusSelected = {

                }
            )
        }
    }
}