package com.zlucas2k.mytask.presentation.screens.task.components.pickers

import android.app.TimePickerDialog
import android.content.Context
import android.content.res.Configuration
import android.text.format.DateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import java.util.Calendar

@Composable
fun TimePicker(
    value: String,
    onValueChange: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .height(TextFieldDefaults.MinHeight)
            .clickable {
                showTimerPickerDialog(context) { hour, minute ->
                    onValueChange(hour, minute)
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
                text = stringResource(id = R.string.time),
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
            .background(color = MaterialTheme.colors.primaryVariant.copy(alpha = 0.3f))
    ) {
        Icon(
            imageVector = Icons.Filled.Alarm,
            contentDescription = null,
            tint = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .size(45.dp)
                .align(Alignment.Center)
        )
    }
}

private fun showTimerPickerDialog(context: Context, onTimeValue: (Int, Int) -> Unit) {
    val calendar = Calendar.getInstance()
    val calendarHours = calendar.get(Calendar.HOUR_OF_DAY)
    val calendarMinutes = calendar.get(Calendar.MINUTE)
    val isSystem24Hour = DateFormat.is24HourFormat(context)

    val timePickerDialog = TimePickerDialog(
        context, { _, hour: Int, minute: Int ->
            onTimeValue(hour, minute)
        }, calendarHours, calendarMinutes, isSystem24Hour
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
                onValueChange = { _, _ ->

                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}