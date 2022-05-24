package com.zlucas2k.mytask.presentation.screens.task.components

import android.content.res.Configuration
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme

@Composable
fun TaskTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    textStyle: TextStyle,
    placeholderText: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    singleLine: Boolean,
    maxLines: Int
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        readOnly = readOnly,
        textStyle = textStyle,
        placeholder = {
            Text(
                text = placeholderText,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
            )
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.primary,
            placeholderColor = MaterialTheme.colors.primaryVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = MaterialTheme.colors.primary
        )
    )
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        TaskTextField(
            value = "Estudar Kotlin",
            onValueChange = {},
            textStyle = MaterialTheme.typography.h1,
            placeholderText = "Título",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {}
            ),
            singleLine = true,
            maxLines = 1
        )
    }
}