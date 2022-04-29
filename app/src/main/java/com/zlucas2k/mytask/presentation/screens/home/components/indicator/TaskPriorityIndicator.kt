package com.zlucas2k.mytask.presentation.screens.home.components.indicator

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.presentation.common.model.PriorityView
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun TaskPriorityIndicator(
    priority: PriorityView,
    modifier: Modifier = Modifier
) {
    val colorPriorityIndicator = if (isSystemInDarkTheme()) {
        priority.colorDark
    } else {
        priority.colorLight
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.height(TextFieldDefaults.MinHeight / 2)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.01f)
                .background(colorPriorityIndicator)
        )

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = getPriorityName(priority),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
        )
    }
}

// TODO: Melhorar essa implementação
private fun getPriorityName(priority: PriorityView) = when (priority) {
    PriorityView.NONE -> "Sem"
    PriorityView.LOW -> "Baixa"
    PriorityView.MEDIUM -> "Média"
    PriorityView.HIGH -> "Alta"
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        TaskPriorityIndicator(
            priority = PriorityView.HIGH,
            modifier = Modifier.fillMaxWidth()
        )
    }
}