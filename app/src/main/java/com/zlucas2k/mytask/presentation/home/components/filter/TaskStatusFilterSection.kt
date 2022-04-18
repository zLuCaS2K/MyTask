package com.zlucas2k.mytask.presentation.home.components.filter

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.components.RoundedChip
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.home.common.filter.TaskStatusFilter

@Composable
fun TaskStatusFilterSection(
    filter: TaskStatusFilter,
    onFilterChange: (TaskStatusFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    val listFilterOptions = listOf(
        TaskStatusFilter.ALL,
        TaskStatusFilter.TODO,
        TaskStatusFilter.PROGRESS,
        TaskStatusFilter.DONE,
    )

    val listTextFilterOptions = listOf(
        "Todos",
        stringResource(id = R.string.todo),
        stringResource(id = R.string.progress),
        stringResource(id = R.string.done),
    )

    LazyRow(modifier = modifier) {
        items(listFilterOptions) { filterOption ->
            RoundedChip(
                text = listTextFilterOptions[filterOption.ordinal],
                isSelected = filter == filterOption,
                onClick = {
                    onFilterChange(filterOption)
                }
            )
        }
    }
}

@Composable
@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {

        var filterSelected by remember { mutableStateOf(TaskStatusFilter.ALL) }

        TaskStatusFilterSection(
            filter = filterSelected,
            onFilterChange = {
                filterSelected = it
            }
        )
    }
}