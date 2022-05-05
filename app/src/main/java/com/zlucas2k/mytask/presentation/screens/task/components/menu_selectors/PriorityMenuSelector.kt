package com.zlucas2k.mytask.presentation.screens.task.components.menu_selectors

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.presentation.common.model.PriorityView
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.screens.home.components.indicator.TaskPriorityIndicator
import com.zlucas2k.mytask.presentation.screens.task.components.dropdowns.PriorityDropDownMenu

@Composable
fun PriorityMenuSelector(
    priority: PriorityView,
    onPriorityChange: (PriorityView) -> Unit,
    modifier: Modifier = Modifier
) {
    var menuExpandedState by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(TextFieldDefaults.MinHeight / 2)
            .clickable {
                menuExpandedState = true
            }
    ) {
        TaskPriorityIndicator(priority = priority)

        PriorityDropDownMenu(
            onPriorityChange = { prioritySelected ->
                onPriorityChange(prioritySelected)
            },
            menuExpandedState = menuExpandedState,
            menuExpandedStateChange = {
                menuExpandedState = false
            }
        )
    }
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {

        var priority by remember { mutableStateOf(PriorityView.NONE) }

        PriorityMenuSelector(
            priority = priority,
            onPriorityChange = {
                priority = it
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}