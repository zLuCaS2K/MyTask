package com.zlucas2k.mytask.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun MyTaskDropDownMenu(
    items: List<String>,
    onItemIndexChange: (Int) -> Unit,
    expandedState: Boolean,
    onExpandedStateChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenu(
        expanded = expandedState,
        onDismissRequest = { onExpandedStateChange() },
        modifier = modifier
    ) {
        items.forEachIndexed { index, label ->
            DropdownMenuItem(
                onClick = {
                    onItemIndexChange(index)
                    onExpandedStateChange()
                },
                content = {
                    Text(
                        text = label,
                        color = MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.subtitle2
                    )
                }
            )
        }
    }
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        val items = listOf("Item 1", "Item 2", "Item 3")
        var itemSelected by remember { mutableStateOf(items.first()) }
        var expandedState by remember { mutableStateOf(false) }

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = itemSelected,
                modifier = Modifier.clickable { expandedState = true }
            )

            MyTaskDropDownMenu(
                items = items,
                onItemIndexChange = { itemIndexSelected ->
                    itemSelected = items[itemIndexSelected]
                },
                expandedState = expandedState,
                onExpandedStateChange = {
                    expandedState = false
                }
            )
        }
    }
}