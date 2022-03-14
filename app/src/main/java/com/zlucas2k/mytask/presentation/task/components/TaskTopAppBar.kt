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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun TaskTopAppBar(
    isEditing: Boolean,
    onDeleteClick: () -> Unit,
    onSaveClick: () -> Unit,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
        actions = {
            if (isEditing) {
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }

            IconButton(onClick = onSaveClick) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
        elevation = 0.dp
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