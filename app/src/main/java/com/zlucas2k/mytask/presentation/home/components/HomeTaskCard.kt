package com.zlucas2k.mytask.presentation.home.components

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.presentation.common.model.PriorityView
import com.zlucas2k.mytask.presentation.common.model.TaskView
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun HomeTaskCard(task: TaskView, modifier: Modifier = Modifier) {

    val colorPriority = if (isSystemInDarkTheme()) {
        task.priority.colorDark
    } else {
        task.priority.colorLight
    }

    Card(modifier = modifier, elevation = 8.dp) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            Divider(
                color = colorPriority,
                thickness = 1.dp,
                modifier = Modifier
                    .width(2.dp)
                    .fillMaxHeight()
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                )

                Text(
                    text = task.description,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
                    maxLines = 5,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(start = 8.dp, top = 8.dp, end = 8.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onPrimary.copy(0.5f)
                    )

                    Text(
                        text = "${task.date} | ${task.time}",
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}

@Composable
@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    val task = TaskView(
        1,
        "Estudar Kotlin",
        "17/08/2000",
        "18:00",
        "ADsdadsadsadafmdfmadfapfmsfoamsfmaf",
        PriorityView.HIGH
    )

    MyTaskTheme {
        HomeTaskCard(
            task = task,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)
        )
    }
}