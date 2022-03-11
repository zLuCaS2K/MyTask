package com.zlucas2k.mytask.presentation.home.components

import android.content.res.Configuration
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun HomeTopAppBar(
    onClickAbout: () -> Unit,
    onClickSettings: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colors.onPrimary
            )
        },
        actions = {
            IconButton(onClick = onClickAbout) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary
                )
            }

            IconButton(onClick = onClickSettings) {
                Icon(
                    imageVector = Icons.Default.Settings,
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
private fun Preview() {
    MyTaskTheme {
        HomeTopAppBar(
            onClickAbout = {

            },
            onClickSettings = {

            }
        )
    }
}