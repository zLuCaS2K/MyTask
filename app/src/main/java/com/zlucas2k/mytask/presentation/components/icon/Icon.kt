package com.zlucas2k.mytask.presentation.components.icon

import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun MyTaskIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String? = null,
) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        tint = MaterialTheme.colors.onPrimary,
        modifier = modifier
    )
}

@Preview
@Composable
private fun Preview() {
    MyTaskTheme {
        MyTaskIcon(imageVector = Icons.Filled.Search)
    }
}