package com.zlucas2k.mytask.presentation.task.components

import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.*

@Composable
fun TaskTimePicker(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val calendarHours = calendar.get(Calendar.HOUR_OF_DAY)
    val calendarMinutes = calendar.get(Calendar.MINUTE)

    val timePickerDialog = TimePickerDialog(
        context, { _, hour: Int, minute: Int ->
            onValueChange("$hour:$minute")
        }, calendarHours, calendarMinutes, false
    )

    TaskTextField(
        value = value,
        onValueChange = {},
        placeholderText = "Hora",
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary
            )
        },
        readOnly = true,
        singleLine = true,
        maxLines = 1,
        modifier = modifier.clickable {
            timePickerDialog.show()
        }
    )
}