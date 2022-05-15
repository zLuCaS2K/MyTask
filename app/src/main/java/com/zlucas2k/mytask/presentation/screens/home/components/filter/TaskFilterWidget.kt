package com.zlucas2k.mytask.presentation.screens.home.components.filter

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.domain.util.TaskFilter
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.components.chip.RoundedChip

@Composable
fun TaskFilterWidget(
    modifier: Modifier = Modifier,
    taskFilterWidgetState: TaskFilterWidgetState,
) {
    AnimatedVisibility(
        visible = taskFilterWidgetState.isVisible,
        enter = fadeIn(),
        exit = fadeOut(),
        content = {
            TaskFilterWidgetContent(
                filter = taskFilterWidgetState.filterQuery,
                onFilterChange = { filterQuery ->
                    taskFilterWidgetState.onFilterQueryChange(filterQuery)
                },
                modifier = modifier
            )
        }
    )
}

@Composable
private fun TaskFilterWidgetContent(
    filter: TaskFilter,
    onFilterChange: (TaskFilter) -> Unit,
    modifier: Modifier
) {
    val filterOptions = listOf(
        TaskFilter.All,
        TaskFilter.Todo,
        TaskFilter.Progress,
        TaskFilter.Done
    )

    val filterTextOptions = listOf(
        "Todos",
        stringResource(id = R.string.todo),
        stringResource(id = R.string.progress),
        stringResource(id = R.string.done),
    )

    // First is text, second is value
    val filters = filterTextOptions zip filterOptions

    LazyRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        items(filters) { filterOption ->
            RoundedChip(
                text = filterOption.first,
                isSelected = filter == filterOption.second,
                onClick = {
                    onFilterChange(filterOption.second)
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
        var filterSelected by remember { mutableStateOf<TaskFilter>(TaskFilter.All) }

        TaskFilterWidgetContent(
            filter = filterSelected,
            onFilterChange = { filterQuery ->
                filterSelected = filterQuery
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}