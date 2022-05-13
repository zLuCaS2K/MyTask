package com.zlucas2k.mytask.presentation.screens.task.components.dropdowns

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.model.PriorityView
import com.zlucas2k.mytask.presentation.components.drop_down.MyTaskDropDownMenu

@Composable
fun PriorityDropDownMenu(
    onPriorityChange: (PriorityView) -> Unit,
    menuExpandedState: Boolean,
    menuExpandedStateChange: () -> Unit,
) {

    val priorityItems = listOf(
        stringResource(id = R.string.none),
        stringResource(id = R.string.low),
        stringResource(id = R.string.medium),
        stringResource(id = R.string.high)
    )

    val priorityItemsModels = listOf(
        PriorityView.NONE,
        PriorityView.LOW,
        PriorityView.MEDIUM,
        PriorityView.HIGH,
    )

    MyTaskDropDownMenu(
        items = priorityItems,
        onItemIndexChange = { indexOptionSelected ->
            onPriorityChange(
                priorityItemsModels[indexOptionSelected]
            )
        },
        expandedState = menuExpandedState,
        onExpandedStateChange = menuExpandedStateChange
    )
}