package com.zlucas2k.mytask.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun TopAppBarDefault(
    title: String,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.onPrimary
            )
        },
        actions = actions
    )
}

@Preview
@Composable
private fun Preview() {
    MyTaskTheme {
        TopAppBarDefault(
            title = stringResource(id = R.string.app_name),
            actions = {

            }
        )
    }
}