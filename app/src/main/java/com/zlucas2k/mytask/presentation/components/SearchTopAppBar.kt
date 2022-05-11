package com.zlucas2k.mytask.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun MyTaskSearchTopAppBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClicked: () -> Unit,
    onCloseClicked: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }

    Surface(
        color = MaterialTheme.colors.primary,
        elevation = AppBarDefaults.TopAppBarElevation,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        TextField(
            value = query,
            onValueChange = { onQueryChange(it) },
            textStyle = TextStyle(fontSize = MaterialTheme.typography.subtitle1.fontSize),
            placeholder = {
                Text(
                    text = "Pesquisar",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
                    modifier = Modifier.alpha(ContentAlpha.medium)
                )
            },
            leadingIcon = {
                MyTaskIconButton(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    onClick = onSearchClicked
                )
            },
            trailingIcon = {
                MyTaskIconButton(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    onClick = {
                        if (query.isNotEmpty()) {
                            onQueryChange("")
                        } else {
                            onCloseClicked()
                        }
                    }
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked()
                }
            ),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            ),
            modifier = Modifier.focusRequester(focusRequester)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MyTaskTheme {
        MyTaskSearchTopAppBar(
            query = "",
            onQueryChange = {},
            onSearchClicked = {},
            onCloseClicked = {}
        )
    }
}