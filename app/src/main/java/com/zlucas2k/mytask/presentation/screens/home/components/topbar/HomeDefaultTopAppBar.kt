package com.zlucas2k.mytask.presentation.screens.home.components.topbar

import android.content.res.Configuration
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun HomeDefaultTopAppBar(
    onSearchClicked: () -> Unit,
    onFilterClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colors.onPrimary
            )
        },
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary
                )
            }

            IconButton(onClick = onFilterClicked) {
                Icon(
                    imageVector = Icons.Filled.FilterAlt,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    )
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun HomeDefaultTopAppBarPreview() {
    MyTaskTheme {
        HomeDefaultTopAppBar(
            onSearchClicked = {},
            onFilterClicked = {}
        )
    }
}