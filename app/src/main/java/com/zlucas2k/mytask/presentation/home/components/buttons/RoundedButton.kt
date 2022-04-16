package com.zlucas2k.mytask.presentation.home.components.buttons

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.caption,
    onClick: () -> Unit,
    isSelected: Boolean = true
) {
    val selectedAlpha = if (isSelected) 1f else 0.5f

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(percent = 50),
        border = BorderStroke(width = 1.dp, MaterialTheme.colors.onPrimary),
        modifier = modifier.alpha(selectedAlpha),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        )
    ) {
        Text(
            text = text,
            style = textStyle,
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun PreviewDark() {
    MyTaskTheme {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            RoundedButton(
                text = "Hello World",
                onClick = {},
                isSelected = true
            )

            RoundedButton(
                text = "Hello World",
                onClick = {},
                isSelected = false
            )
        }
    }
}