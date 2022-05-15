package com.zlucas2k.mytask.presentation.screens.home.components.topbar.home

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.components.icon.MyTaskIconButton
import com.zlucas2k.mytask.presentation.components.top_bar.MyTaskTopAppBar

@Composable
fun HomeTopAppBar(
    onSearchClicked: () -> Unit,
    onFilterClicked: () -> Unit
) {
    MyTaskTopAppBar(
        title = stringResource(id = R.string.app_name),
        actions = {
            MyTaskIconButton(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                onClick = onSearchClicked
            )

            MyTaskIconButton(
                imageVector = Icons.Filled.FilterAlt,
                contentDescription = null,
                onClick = onFilterClicked
            )
        }
    )
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun HomeDefaultTopAppBarPreview() {
    MyTaskTheme {
        HomeTopAppBar(
            onSearchClicked = {},
            onFilterClicked = {}
        )
    }
}