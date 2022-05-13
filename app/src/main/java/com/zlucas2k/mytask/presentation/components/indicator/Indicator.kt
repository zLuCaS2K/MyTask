package com.zlucas2k.mytask.presentation.components.indicator

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.components.icon.MyTaskIcon

@Composable
fun MyTaskIndicator(
    imageVector: ImageVector,
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(TextFieldDefaults.MinHeight)
            .clickable { onClick() }
    ) {
        IndicatorIconRounded(imageVector = imageVector)

        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = title,
                color = MaterialTheme.colors.onPrimary.copy(alpha = 0.8f),
                style = MaterialTheme.typography.caption
            )

            Text(
                text = value,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(start = 5.dp)
            )
        }
    }
}

@Composable
private fun IndicatorIconRounded(imageVector: ImageVector) {
    Box(
        modifier = Modifier
            .padding(start = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colors.primaryVariant.copy(alpha = 0.2f))
    ) {
        MyTaskIcon(
            imageVector = imageVector,
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        MyTaskIndicator(
            imageVector = Icons.Filled.Alarm,
            title = "Hora",
            value = "18:30",
            modifier = Modifier.fillMaxWidth()
        )
    }
}