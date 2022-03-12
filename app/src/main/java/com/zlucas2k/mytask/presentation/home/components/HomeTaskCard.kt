package com.zlucas2k.mytask.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.domain.model.Task

@Composable
fun HomeTaskCard(
    task: Task,
    onEditTask: () -> Unit,
    onDeleteTask: () -> Unit,
    modifier: Modifier
) {
    Card(modifier = modifier) {
        Box(modifier = Modifier.fillMaxSize()) {
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.01f)
                    .background(MaterialTheme.colors.primary)
                    .align(Alignment.CenterStart)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                )

                Text(
                    text = task.description,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
                    maxLines = 5,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp, end = 8.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                ) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
                    Text(text = "${task.date} | ${task.time}")

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = onEditTask) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                        }

                        IconButton(onClick = onDeleteTask) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}