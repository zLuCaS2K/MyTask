package com.zlucas2k.mytask.presentation.task.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.*

@Composable
fun TaskDatePicker(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val calendarYear = calendar.get(Calendar.YEAR)
    val calendarMonth = calendar.get(Calendar.MONTH)
    val calendarDay = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(context, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            onValueChange("$dayOfMonth/$month/$year")
        }, calendarYear, calendarMonth, calendarDay
    )

    TaskTextField(
        value = value,
        onValueChange = {},
        placeholderText = "Data",
        leadingIcon = { Icon(imageVector = Icons.Filled.DateRange, contentDescription = null) },
        readOnly = true,
        singleLine = true,
        maxLines = 1,
        modifier = modifier.clickable {
            datePickerDialog.show()
        }
    )
}