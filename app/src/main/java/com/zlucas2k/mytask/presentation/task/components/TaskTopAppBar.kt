package com.zlucas2k.mytask.presentation.task.components

import android.content.res.Configuration
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun TaskTopAppBar(
    isEditing: Boolean,
    onDeleteClick: () -> Unit,
    onSaveClick: () -> Unit,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
        actions = {
            if (isEditing) {
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = stringResource(id = R.string.delete_task),
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }

            IconButton(onClick = onSaveClick) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = stringResource(id = R.string.save_sucess_task),
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
        TaskTopAppBar(
            isEditing = true,
            onDeleteClick = { },
            onSaveClick = { },
            onBackPressed = { }
        )
    }
}