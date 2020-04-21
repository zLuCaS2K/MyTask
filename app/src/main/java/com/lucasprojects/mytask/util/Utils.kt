package com.lucasprojects.mytask.util

import android.graphics.Color
import android.widget.TextView
import com.lucasprojects.mytask.constants.TaskConstants

class Utils {

    companion object {
        fun setColorPriority(textView: TextView) {
            when (textView.text) {
                TaskConstants.PRIORITIES.LOW -> textView.setTextColor(Color.GREEN)
                TaskConstants.PRIORITIES.MEDIUM -> textView.setTextColor(Color.YELLOW)
                TaskConstants.PRIORITIES.HIGH -> textView.setTextColor(Color.rgb(255, 165, 0))
                TaskConstants.PRIORITIES.CRITICAL -> textView.setTextColor(Color.RED)
            }
        }
    }
}