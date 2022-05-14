package com.zlucas2k.mytask.presentation.screens.home.components.sheet

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.model.TaskView
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.components.indicator.MyTaskIndicator
import com.zlucas2k.mytask.presentation.components.sheet.MyTaskBottomSheet
import com.zlucas2k.mytask.presentation.screens.home.components.indicator.TaskPriorityIndicator
import com.zlucas2k.mytask.presentation.screens.home.components.indicator.TaskStatusIndicator

@Composable
@ExperimentalMaterialApi
fun TaskDetailBottomSheet(
    taskDetailBottomSheetState: TaskDetailBottomSheetState,
    screenContent: @Composable () -> Unit
) {
    MyTaskBottomSheet(
        sheetState = taskDetailBottomSheetState.sheetState,
        sheetContent = {
            TaskDetailBottomSheetContent(
                taskDetail = taskDetailBottomSheetState.taskDetail,
                modifier = Modifier.fillMaxWidth()
            )
        },
        screenContent = screenContent
    )
}

@Composable
private fun TaskDetailBottomSheetContent(
    modifier: Modifier = Modifier,
    taskDetail: TaskView
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(top = 10.dp, bottom = 20.dp),
    ) {

        val modifierComponents = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 10.dp, end = 10.dp)

        Text(
            text = taskDetail.title,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onPrimary,
            maxLines = 2,
            modifier = modifierComponents
        )

        Row {
            TaskPriorityIndicator(
                priority = taskDetail.priority,
                modifier = modifierComponents.weight(1f)
            )

            TaskStatusIndicator(
                status = taskDetail.status,
                modifier = modifierComponents.weight(1f)
            )
        }

        // Indicator for time
        MyTaskIndicator(
            imageVector = Icons.Filled.Alarm,
            title = stringResource(id = R.string.time),
            value = taskDetail.time,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp, end = 10.dp)
        )

        // Indicator for date
        MyTaskIndicator(
            imageVector = Icons.Filled.DateRange,
            title = stringResource(id = R.string.date),
            value = taskDetail.date,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp, end = 10.dp)
        )

        Text(
            text = taskDetail.description,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onPrimary,
            maxLines = 20,
            modifier = modifierComponents
        )
    }
}

@Composable
@ExperimentalMaterialApi
@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        val task = TaskView(
            title = "Titulo",
            date = "Data",
            time = "Hora",
            description = "It is a long established fact that a reader will be distracted by the " +
                    "readable content of a page when looking at its layout. The point of using Lorem Ipsum" +
                    " is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here," +
                    " content here', making it look like readable English.",

            )

        TaskDetailBottomSheetContent(
            taskDetail = task,
            modifier = Modifier.fillMaxWidth()
        )
    }
}