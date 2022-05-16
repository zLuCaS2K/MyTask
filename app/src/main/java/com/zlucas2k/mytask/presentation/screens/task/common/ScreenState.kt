package com.zlucas2k.mytask.presentation.screens.task.common

import androidx.compose.material.ScaffoldState
import kotlinx.coroutines.flow.SharedFlow

interface ScreenState<E> {
    val scaffoldState: ScaffoldState
    val uiEvent: SharedFlow<E>

    fun showSnackbar(message: String)
}