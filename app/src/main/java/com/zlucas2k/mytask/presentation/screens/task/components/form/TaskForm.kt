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
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.screens.task.components.TaskTextField
import com.zlucas2k.mytask.presentation.screens.task.components.menu_selectors.PriorityMenuSelector
import com.zlucas2k.mytask.presentation.screens.task.components.menu_selectors.StatusMenuSelector
import com.zlucas2k.mytask.presentation.screens.task.components.pickers.DatePicker
import com.zlucas2k.mytask.presentation.screens.task.components.pickers.TimePicker

@Composable
fun TaskForm(
    modifier: Modifier = Modifier,
    isEditing: Boolean = false,
    taskFormState: TaskFormState,
    onTimeChange: (Int, Int) -> Unit,
    onDateChange: (String) -> Unit
) {
    val modifierComponents = Modifier.fillMaxWidth()

    Column(modifier = modifier) {
        TaskTextField(
            value = taskFormState.title,
            onValueChange = { taskFormState.title = it },
            textStyle = MaterialTheme.typography.h1,
            placeholderText = stringResource(id = R.string.title_task),
            singleLine = true,
            maxLines = 1,
            modifier = modifierComponents
        )

        Row {
            PriorityMenuSelector(
                priority = taskFormState.priority,
                onPriorityChange = { taskFormState.priority = it },
                modifier = modifierComponents
                    .weight(1f)
                    .padding(start = 20.dp, top = 10.dp, end = 10.dp)
            )

            if (isEditing) {
                StatusMenuSelector(
                    status = taskFormState.status,
                    onStatusChange = { taskFormState.status = it },
                    modifier = modifierComponents
                        .weight(1f)
                        .padding(start = 20.dp, top = 10.dp, end = 10.dp)
                )
            }
        }

        TimePicker(
            value = taskFormState.time,
            onValueChange = { hour, minute -> onTimeChange(hour, minute) },
            modifier = modifierComponents.padding(start = 10.dp, top = 10.dp, end = 10.dp)
        )

        DatePicker(
            value = taskFormState.date,
            onValueChange = { date -> onDateChange(date) },
            modifier = modifierComponents.padding(start = 10.dp, top = 10.dp, end = 10.dp)
        )

        TaskTextField(
            value = taskFormState.description,
            onValueChange = { taskFormState.description = it },
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

        }
    }
}