package com.zlucas2k.mytask.presentation.components.widget

/**
 * @property isVisible: Informs whether the widget is visible or hidden.
 */
interface WidgetState {
    val isVisible: Boolean

    fun openWidget()
    fun closeWidget()
}