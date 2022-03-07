package com.zlucas2k.mytask.presentation.task.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.domain.model.Priority
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun TaskScreenContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit,
    date: String,
    onDateChange: (String) -> Unit,
    completed: Boolean,
    onCompletedChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { onTitleChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp, end = 10.dp),
            label = { Text(text = "Titulo") },
            textStyle = MaterialTheme.typography.body1,
            singleLine = true
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TaskPriorityDropDown(
                priority = priority,
                onPrioritySelected = onPrioritySelected,
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
            )

            OutlinedButton(
                onClick = { /* TODO: Abrir DatePicker */ },
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(10.dp)
                    .weight(1f)
            ) {
                Text(text = "17/08/2000")
            }
        }

        OutlinedTextField(
            value = description,
            onValueChange = { onDescriptionChange(it) },
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            label = { Text(text = "Descrição") },
            textStyle = MaterialTheme.typography.body1
        )
    }
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        var title by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        var priority by remember { mutableStateOf(Priority.HIGH) }
        var date by remember { mutableStateOf("17/08/2000") }
        var completed by remember { mutableStateOf(false) }

        TaskScreenContent(
            title = title,
            onTitleChange = {
                title = it
            },
            description = description,
            onDescriptionChange = {
                description = it
            },
            priority = priority,
            onPrioritySelected = {
                priority = it
            },
            date = date,
            onDateChange = {
                date = it
            },
            completed = completed,
            onCompletedChange = {
                completed = it
            }
        )
    }
}