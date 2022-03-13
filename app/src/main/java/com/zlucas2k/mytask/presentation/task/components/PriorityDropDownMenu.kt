package com.zlucas2k.mytask.presentation.task.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.domain.model.Priority
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun PriorityDropDownMenu(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit,
    modifier: Modifier = Modifier
) {
    val expanded = remember { mutableStateOf(false) }
    val colorPriority = if (isSystemInDarkTheme()) priority.colorDark else priority.colorLight

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(TextFieldDefaults.MinHeight / 2)
            .clickable { expanded.value = true }
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.01f)
                .background(colorPriority)
        )

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = getPriorityName(priority),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
        )

        DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
            DropdownMenuItem(
                onClick = {
                    expanded.value = false
                    onPrioritySelected(Priority.NONE)
                },
                content = {
                    PriorityDropDownMenuItem("Sem")
                }
            )

            DropdownMenuItem(
                onClick = {
                    expanded.value = false
                    onPrioritySelected(Priority.LOW)
                },
                content = {
                    PriorityDropDownMenuItem("Baixa")
                }
            )

            DropdownMenuItem(
                onClick = {
                    expanded.value = false
                    onPrioritySelected(Priority.MEDIUM)
                },
                content = {
                    PriorityDropDownMenuItem("Média")
                }
            )

            DropdownMenuItem(
                onClick = {
                    expanded.value = false
                    onPrioritySelected(Priority.HIGH)
                },
                content = {
                    PriorityDropDownMenuItem("Alta")
                }
            )
        }
    }
}

@Composable
private fun PriorityDropDownMenuItem(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colors.onPrimary,
        style = MaterialTheme.typography.subtitle2
    )
}

private fun getPriorityName(priority: Priority) = when (priority) {
    Priority.NONE -> "Sem"
    Priority.LOW -> "Baixa"
    Priority.MEDIUM -> "Média"
    Priority.HIGH -> "Alta"
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        PriorityDropDownMenu(
            priority = Priority.HIGH,
            onPrioritySelected = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}