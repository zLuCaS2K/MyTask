package com.zlucas2k.mytask.presentation.home.components

import android.content.res.Configuration
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun HomeTaskCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp)
    ) {
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
                    text = "Coisas para estudar",
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                )

                Text(
                    text = "It is a long established fact that a reader will be distracted by the " +
                            "readable content of a page when looking at its layout. The point of using" +
                            " Lorem Ipsum is that it has a more-or-less normal distribution of letters",
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
                    Text(text = "17/08/2000")

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = { /* TODO: Editar tarefa pelo Card */ }) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                        }

                        IconButton(onClick = { /* TODO: Deletar tarefa pelo Card */ }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        HomeTaskCard()
    }
}