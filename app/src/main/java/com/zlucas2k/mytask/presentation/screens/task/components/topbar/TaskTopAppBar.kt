package com.zlucas2k.mytask.presentation.screens.task.components.topbar

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.components.MyTaskIconButton
import com.zlucas2k.mytask.presentation.components.MyTaskTopAppBar

@Composable
fun TaskTopAppBar(
    isEditing: Boolean,
    onDeleteClick: () -> Unit,
    onSaveClick: () -> Unit,
    onBackPressed: () -> Unit
) {
    MyTaskTopAppBar(
        title = "",
        navigationIcon = {
            MyTaskIconButton(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                onClick = onBackPressed
            )
        },
        actions = {
            if (isEditing) {
                MyTaskIconButton(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = stringResource(id = R.string.delete_task),
                    onClick = onDeleteClick
                )
            }

            MyTaskIconButton(
                imageVector = Icons.Filled.Save,
                contentDescription = stringResource(id = R.string.save_task),
                onClick = onSaveClick
            )
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