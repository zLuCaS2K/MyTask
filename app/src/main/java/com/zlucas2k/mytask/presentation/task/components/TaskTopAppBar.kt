package com.zlucas2k.mytask.presentation.task.components

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.common.utils.Action
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun TaskTopAppBar(
    selectedTask: Task?,

) {

}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        TaskTopAppBar(null)
    }
}