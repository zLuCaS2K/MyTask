package com.zlucas2k.mytask.presentation.components.card

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun MyTaskCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = 8.dp,
        content = content
    )
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        MyTaskCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(MaterialTheme.colors.onPrimary),
            content = {

            }
        )
    }
}