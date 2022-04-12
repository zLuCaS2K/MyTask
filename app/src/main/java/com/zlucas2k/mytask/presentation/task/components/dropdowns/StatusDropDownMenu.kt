package com.zlucas2k.mytask.presentation.task.components.dropdowns

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.presentation.common.model.StatusView
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun StatusDropDownMenu(
    status: StatusView,
    onStatusSelected: (StatusView) -> Unit,
    modifier: Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(TextFieldDefaults.MinHeight / 2)
            .clickable { expanded = true }
    ) {
        Icon(
            imageVector = status.icon,
            contentDescription = null,
            tint = MaterialTheme.colors.onPrimary
        )

        Spacer(modifier = Modifier.size(10.dp))

        StatusTextIndicator(status)

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onStatusSelected(StatusView.TODO)
                },
                content = {
                    StatusTextIndicator(StatusView.TODO)
                }
            )

            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onStatusSelected(StatusView.PROGRESS)
                },
                content = {
                    StatusTextIndicator(StatusView.PROGRESS)
                }
            )

            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onStatusSelected(StatusView.DONE)
                },
                content = {
                    StatusTextIndicator(StatusView.DONE)
                }
            )
        }
    }
}

@Composable
private fun StatusTextIndicator(status: StatusView) {
    val text = when (status) {
        StatusView.TODO -> "Agendado"
        StatusView.PROGRESS -> "Em andamento"
        StatusView.DONE -> "Concluida"
    }

    Text(
        text = text,
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
    )
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        StatusDropDownMenu(
            status = StatusView.TODO,
            onStatusSelected = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}