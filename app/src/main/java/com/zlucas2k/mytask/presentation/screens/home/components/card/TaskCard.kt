package com.zlucas2k.mytask.presentation.screens.home.components.card

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.model.PriorityView
import com.zlucas2k.mytask.presentation.common.model.StatusView
import com.zlucas2k.mytask.presentation.common.model.TaskView
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.components.card.MyTaskCard

@Composable
fun TaskCard(task: TaskView, modifier: Modifier = Modifier) {

    val colorPriority = if (isSystemInDarkTheme()) {
        task.priority.colorDark
    } else {
        task.priority.colorLight
    }

    MyTaskCard(modifier = modifier.wrapContentHeight(CenterVertically)) {
        Row {
            IndicatorTaskPriorityColor(
                color = colorPriority
            )

            Column(modifier = Modifier.wrapContentHeight()) {
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
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp)
                )

                TaskCardFooter(
                    date = task.date,
                    time = task.time,
                    status = task.status
                )
            }
        }

    }
}

@Composable
private fun IndicatorTaskPriorityColor(color: Color) {
    Divider(
        color = color,
        modifier = Modifier
            .fillMaxHeight()
            .width(2.dp)
    )
}

@Composable
private fun TaskCardFooter(
    date: String,
    time: String,
    status: StatusView
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
    ) {
        TaskSheduleTimeIndicator(
            date = date,
            time = time,
            modifier = Modifier.align(Alignment.CenterStart)
        )

        TaskStatusIndicator(
            status = status,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Composable
private fun TaskSheduleTimeIndicator(
    date: String,
    time: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Icon(
            imageVector = Icons.Filled.DateRange,
            contentDescription = null,
            tint = MaterialTheme.colors.onPrimary.copy(0.5f)
        )

        Text(
            text = date,
            style = MaterialTheme.typography.caption
        )

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            imageVector = Icons.Filled.Alarm,
            contentDescription = null,
            tint = MaterialTheme.colors.onPrimary.copy(0.5f)
        )

        Text(
            text = time,
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
private fun TaskStatusIndicator(
    status: StatusView,
    modifier: Modifier = Modifier
) {
    val statusName = when (status) {
        StatusView.TODO -> stringResource(id = R.string.todo)
        StatusView.PROGRESS -> stringResource(id = R.string.progress)
        StatusView.DONE -> stringResource(id = R.string.done)
    }

    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onPrimary.copy(0.5f),
                shape = RoundedCornerShape(100.dp)
            )
    ) {
        Text(
            text = statusName,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 8.dp)
        )
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
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.",
        PriorityView.HIGH,
        StatusView.TODO
    )

    MyTaskTheme {
        TaskCard(
            task = task,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}