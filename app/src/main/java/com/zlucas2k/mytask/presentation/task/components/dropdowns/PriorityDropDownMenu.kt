package com.zlucas2k.mytask.presentation.task.components.dropdowns

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
import com.zlucas2k.mytask.presentation.common.model.PriorityView
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun PriorityDropDownMenu(
    priority: PriorityView,
    onPrioritySelected: (PriorityView) -> Unit,
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
                    onPrioritySelected(PriorityView.NONE)
                },
                content = {
                    PriorityDropDownMenuItem("Sem")
                }
            )

            DropdownMenuItem(
                onClick = {
                    expanded.value = false
                    onPrioritySelected(PriorityView.LOW)
                },
                content = {
                    PriorityDropDownMenuItem("Baixa")
                }
            )

            DropdownMenuItem(
                onClick = {
                    expanded.value = false
                    onPrioritySelected(PriorityView.MEDIUM)
                },
                content = {
                    PriorityDropDownMenuItem("Média")
                }
            )

            DropdownMenuItem(
                onClick = {
                    expanded.value = false
                    onPrioritySelected(PriorityView.HIGH)
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

private fun getPriorityName(priority: PriorityView) = when (priority) {
    PriorityView.NONE -> "Sem"
    PriorityView.LOW -> "Baixa"
    PriorityView.MEDIUM -> "Média"
    PriorityView.HIGH -> "Alta"
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        PriorityDropDownMenu(
            priority = PriorityView.HIGH,
            onPrioritySelected = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}