package com.zlucas2k.mytask.presentation.screens.task.components.form

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.presentation.common.model.PriorityView
import com.zlucas2k.mytask.presentation.common.model.StatusView
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.screens.task.components.TaskTextField
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.screens.task.components.menu_selectors.PriorityMenuSelector
import com.zlucas2k.mytask.presentation.screens.task.components.menu_selectors.StatusMenuSelector
import com.zlucas2k.mytask.presentation.screens.task.components.pickers.DatePicker
import com.zlucas2k.mytask.presentation.screens.task.components.pickers.TimePicker

@Composable
fun TaskForm(
    modifier: Modifier = Modifier,
    isEditing: Boolean = false,
    title: String,
    onTitleChange: (String) -> Unit,
    time: String,
    onTimeChange: (Int, Int) -> Unit,
    date: String,
    onDateChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: PriorityView,
    onPriorityChange: (PriorityView) -> Unit,
    status: StatusView,
    onStatusChange: (StatusView) -> Unit
) {
    val modifierComponents = Modifier.fillMaxWidth()

    Column(modifier = modifier) {
        TaskTextField(
            value = title,
            onValueChange = { onTitleChange(it) },
            textStyle = MaterialTheme.typography.h1,
            placeholderText = stringResource(id = R.string.title_task),
            singleLine = true,
            maxLines = 1,
            modifier = modifierComponents
        )

        Row {
            PriorityMenuSelector(
                priority = priority,
                onPriorityChange = { onPriorityChange(it) },
                modifier = modifierComponents
                    .weight(1f)
                    .padding(start = 20.dp, top = 10.dp, end = 10.dp)
            )

            if (isEditing) {
                StatusMenuSelector(
                    status = status,
                    onStatusChange = { onStatusChange(it) },
                    modifier = modifierComponents
                        .weight(1f)
                        .padding(start = 20.dp, top = 10.dp, end = 10.dp)
                )
            }
        }

        TimePicker(
            value = time,
            onValueChange = { hour, minute ->
                onTimeChange(hour, minute)
            },
            modifier = modifierComponents.padding(start = 10.dp, top = 10.dp, end = 10.dp)
        )

        DatePicker(
            value = date,
            onValueChange = { onDateChange(it) },
            modifier = modifierComponents.padding(start = 10.dp, top = 10.dp, end = 10.dp)
        )

        TaskTextField(
            value = description,
            onValueChange = { onDescriptionChange(it) },
            textStyle = MaterialTheme.typography.body2,
            placeholderText = stringResource(id = R.string.description),
            singleLine = false,
            maxLines = 20,
            modifier = Modifier.fillMaxHeight()
        )
    }
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        Surface(color = MaterialTheme.colors.background) {
            TaskForm(
                title = "Titulo da Tarefa",
                onTitleChange = {},
                time = "18:00",
                onTimeChange = { hour, minute -> },
                date = "17/08/2000",
                onDateChange = {},
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
                        " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an" +
                        " unknown printer took a galley of type and scrambled it to make a type specimen book.",
                onDescriptionChange = {},
                priority = PriorityView.HIGH,
                onPriorityChange = {},
                status = StatusView.DONE,
                onStatusChange = {}
            )
        }
    }
}