package com.lucasprojects.mytask.util

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.constants.TaskConstants

class Utils {

    companion object {
        fun setColorPriority(textView: TextView, context: Context) {
            when (textView.text) {
                TaskConstants.PRIORITIES.LOW -> textView.setTextColor(ContextCompat.getColor(context, R.color.colorPriorityLow))
                TaskConstants.PRIORITIES.MEDIUM -> textView.setTextColor(ContextCompat.getColor(context, R.color.colorPriorityMedium))
                TaskConstants.PRIORITIES.HIGH -> textView.setTextColor(ContextCompat.getColor(context, R.color.colorPriorityHigh))
                TaskConstants.PRIORITIES.CRITICAL -> textView.setTextColor(ContextCompat.getColor(context, R.color.colorPriorityCritical))
            }
        }

        fun showToastTask(context: Context, complete: Boolean) {
            if (complete) {
                Toast.makeText(context, context.getString(R.string.task_todo_toast), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, context.getString(R.string.task_done_toast), Toast.LENGTH_SHORT).show()
            }
        }
    }
}