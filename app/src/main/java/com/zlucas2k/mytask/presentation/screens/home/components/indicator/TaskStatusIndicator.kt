package com.zlucas2k.mytask.presentation.screens.home.components.indicator

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.model.StatusView
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.components.MyTaskIcon

@Composable
fun TaskStatusIndicator(
    status: StatusView,
    modifier: Modifier = Modifier
) {
    val nameStatus = when (status) {
        StatusView.TODO -> stringResource(id = R.string.todo)
        StatusView.PROGRESS -> stringResource(id = R.string.progress)
        StatusView.DONE -> stringResource(id = R.string.done)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.height(TextFieldDefaults.MinHeight / 2)
    ) {
        MyTaskIcon(
            imageVector = status.icon
        )

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = nameStatus,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
        )
    }
}

@Composable
@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        TaskStatusIndicator(
            status = StatusView.TODO,
            modifier = Modifier.fillMaxWidth()
        )
    }
}