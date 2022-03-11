package com.zlucas2k.mytask.presentation.task.components

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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
    val colorPriority = if (isSystemInDarkTheme()) priority.colorDark else priority.colorLight

    TaskTextField(
        value = priority.name,
        onValueChange = { },
        placeholderText = "Prioridade",
        leadingIcon = {
            CircleIndicatorPriority(color = colorPriority, size = 16.dp)
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
                onPrioritySelected(Priority.NONE)
            },
            content = {
                PriorityItem(namePriority = "Sem", color = colorPriority)
            }
        )

        DropdownMenuItem(
            onClick = {
                expanded = false
                onPrioritySelected(Priority.LOW)
            },
            content = {
                PriorityItem(namePriority = "Baixa", color = colorPriority)
            }
        )
        DropdownMenuItem(
            onClick = {
                expanded = false
                onPrioritySelected(Priority.MEDIUM)
            },
            content = {
                PriorityItem(namePriority = "Média", color = colorPriority)
            }
        )

        DropdownMenuItem(
            onClick = {
                expanded = false
                onPrioritySelected(Priority.HIGH)
            },
            content = {
                PriorityItem(namePriority = "Alta", color = colorPriority)
            }
        )
    }
}

@Composable
private fun CircleIndicatorPriority(color: Color, size: Dp) {
    Canvas(modifier = Modifier.size(size)) {
        drawCircle(color = color)
    }
}

@Composable
private fun PriorityItem(namePriority: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        CircleIndicatorPriority(color = color, size = 16.dp)

        Text(
            text = namePriority,
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