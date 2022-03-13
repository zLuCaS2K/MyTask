package com.zlucas2k.mytask.presentation.task.components

import android.app.TimePickerDialog
import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import java.util.*

@Composable
fun TimePicker(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .height(TextFieldDefaults.MinHeight)
            .clickable {
                showTimerPickerDialog(context) {
                    onValueChange(it)
                }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconBox()

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(start = 5.dp)
        ) {
            Text(
                text = "Time",
                color = MaterialTheme.colors.onPrimary.copy(alpha = 0.8f),
                style = MaterialTheme.typography.caption
            )

            Text(
                text = value,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(start = 5.dp)
            )
        }

    }
}

@Composable
private fun IconBox() {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(start = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colors.primaryVariant)
    ) {
        Icon(
            imageVector = Icons.Filled.DateRange,
            contentDescription = null,
            tint = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .size(45.dp)
                .align(Alignment.Center)
        )
    }
}

private fun showTimerPickerDialog(context: Context, onTimeValue: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val calendarHours = calendar.get(Calendar.HOUR_OF_DAY)
    val calendarMinutes = calendar.get(Calendar.MINUTE)

    val timePickerDialog = TimePickerDialog(
        context, { _, hour: Int, minute: Int ->
            onTimeValue("$hour:$minute")
        }, calendarHours, calendarMinutes, false
    )

    timePickerDialog.show()
}

@Composable
@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        Surface(color = MaterialTheme.colors.surface) {
            TimePicker(
                value = "13:00",
                onValueChange = {

                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}