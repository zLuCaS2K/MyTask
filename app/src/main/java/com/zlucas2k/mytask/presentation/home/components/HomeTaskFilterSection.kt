package com.zlucas2k.mytask.presentation.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.home.common.filter.TaskStatusFilter
import com.zlucas2k.mytask.presentation.home.components.buttons.RoundedButton

@Composable
fun HomeTaskFilterSection(
    taskStatusFilter: TaskStatusFilter,
    onStatusFilterChange: (TaskStatusFilter) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        RoundedButton(
            text = "Todos",
            onClick = {
                onStatusFilterChange(TaskStatusFilter.ALL)
            },
            isSelected = taskStatusFilter == TaskStatusFilter.ALL,
            modifier = Modifier.weight(1f)
        )

        RoundedButton(
            text = stringResource(id = R.string.todo),
            onClick = {
                onStatusFilterChange(TaskStatusFilter.TODO)
            },
            isSelected = taskStatusFilter == TaskStatusFilter.TODO,
            modifier = Modifier.weight(1f)
        )

        RoundedButton(
            text = stringResource(id = R.string.progress),
            onClick = {
                onStatusFilterChange(TaskStatusFilter.PROGRESS)
            },
            isSelected = taskStatusFilter == TaskStatusFilter.PROGRESS,
            modifier = Modifier.weight(1f)
        )

        RoundedButton(
            text = stringResource(id = R.string.done),
            onClick = {
                onStatusFilterChange(TaskStatusFilter.DONE)
            },
            isSelected = taskStatusFilter == TaskStatusFilter.PROGRESS,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        HomeTaskFilterSection(
            taskStatusFilter = TaskStatusFilter.ALL,
            onStatusFilterChange = {

            }
        )
    }
}