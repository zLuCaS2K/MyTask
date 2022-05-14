package com.zlucas2k.mytask.presentation.screens.home.components.topbar

import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.common.utils.emptyString
import com.zlucas2k.mytask.presentation.components.icon.MyTaskIconButton

@Composable
fun SearchTopAppBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onCloseSearchTopAppBar: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }

    Surface(
        color = MaterialTheme.colors.primary,
        elevation = AppBarDefaults.TopAppBarElevation,
        modifier = modifier.fillMaxWidth(),
    ) {
        TextField(
            value = query,
            onValueChange = { searchQuery ->
                onQueryChange(searchQuery)
            },
            textStyle = TextStyle(fontSize = MaterialTheme.typography.subtitle1.fontSize),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
                    modifier = Modifier.alpha(ContentAlpha.medium)
                )
            },
            leadingIcon = {
                MyTaskIconButton(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                )
            },
            trailingIcon = {
                MyTaskIconButton(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    onClick = {
                        // This is for clean query if not have query.
                        if (query.isNotBlank()) {
                            onQueryChange(emptyString())
                        } else {
                            onCloseSearchTopAppBar()
                        }
                    }
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            ),
            modifier = Modifier.focusRequester(focusRequester)
        )
    }
}