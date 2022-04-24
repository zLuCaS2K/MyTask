package com.zlucas2k.mytask.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun IconButtonDefault(
    imageVector: ImageVector,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = MaterialTheme.colors.onPrimary
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MyTaskTheme {
        IconButtonDefault(
            imageVector = Icons.Filled.Search,
            onClick = {}
        )
    }
}