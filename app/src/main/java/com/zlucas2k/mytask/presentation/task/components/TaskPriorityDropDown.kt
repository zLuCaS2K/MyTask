package com.zlucas2k.mytask.presentation.task.components

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.domain.model.Priority
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun TaskPriorityDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val angel: Float by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    TaskTextField(
        value = priority.name,
        onValueChange = { },
        placeholderText = "Prioridade",
        leadingIcon = {
            Canvas(modifier = Modifier.size(16.dp)) {
                drawCircle(color = priority.color)
            }
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .rotate(degrees = angel)
            )
        },
        readOnly = true,
        singleLine = true,
        maxLines = 1,
        modifier = modifier.clickable {
            expanded = true
        }
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier.fillMaxWidth(fraction = 0.94f)
    ) {
        DropdownMenuItem(
            onClick = {
                expanded = false
                onPrioritySelected(Priority.LOW)
            },
            content = {
                PriorityItem(priority = Priority.LOW)
            }
        )
        DropdownMenuItem(
            onClick = {
                expanded = false
                onPrioritySelected(Priority.MEDIUM)
            },
            content = {
                PriorityItem(priority = Priority.MEDIUM)
            }
        )

        DropdownMenuItem(
            onClick = {
                expanded = false
                onPrioritySelected(Priority.HIGH)
            },
            content = {
                PriorityItem(priority = Priority.HIGH)
            }
        )
    }
}

@Composable
private fun PriorityItem(priority: Priority) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Canvas(modifier = Modifier.size(16.dp)) {
            drawCircle(color = priority.color)
        }

        Text(
            text = priority.name,
            modifier = Modifier.padding(start = 12.dp),
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        TaskPriorityDropDown(
            priority = Priority.HIGH,
            onPrioritySelected = {},
            modifier = Modifier,
        )
    }
}