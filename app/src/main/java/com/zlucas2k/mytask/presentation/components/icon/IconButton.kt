package com.zlucas2k.mytask.presentation.components.icon

import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun MyTaskIconButton(
    imageVector: ImageVector,
    contentDescription: String? = null,
    onClick: () -> Unit = {}
) {
    IconButton(onClick = onClick) {
        MyTaskIcon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MyTaskTheme {
        MyTaskIconButton(
            imageVector = Icons.Filled.Search,
            onClick = {}
        )
    }
}