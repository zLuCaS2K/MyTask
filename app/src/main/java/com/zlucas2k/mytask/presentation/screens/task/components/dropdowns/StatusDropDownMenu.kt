package com.zlucas2k.mytask.presentation.screens.task.components.dropdowns

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.model.StatusView
import com.zlucas2k.mytask.presentation.components.drop_down.MyTaskDropDownMenu

@Composable
fun StatusDropDownMenu(
    onStatusChange: (StatusView) -> Unit,
    menuExpandedState: Boolean,
    menuExpandedStateChange: () -> Unit,
) {
    val statusItems = listOf(
        stringResource(id = R.string.todo),
        stringResource(id = R.string.progress),
        stringResource(id = R.string.done)
    )

    val statusItemsModels = listOf(
        StatusView.TODO,
        StatusView.PROGRESS,
        StatusView.DONE,
    )

    MyTaskDropDownMenu(
        items = statusItems,
        onItemIndexChange = { indexOptionSelected ->
            onStatusChange(
                statusItemsModels[indexOptionSelected]
            )
        },
        expandedState = menuExpandedState,
        onExpandedStateChange = menuExpandedStateChange
    )
}