package com.lucasprojects.mytask.holder

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.entities.TaskEntity
import com.lucasprojects.mytask.entities.listenner.OnTaskListFragmentInteractionListenner
import com.lucasprojects.mytask.repository.cache.PriorityCacheConstants

class TaskViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {

    private val mTextDescription = itemView.findViewById<TextView>(R.id.textDescription)
    private val mTextPriority = itemView.findViewById<TextView>(R.id.textPriority)
    private val mTextDueDate = itemView.findViewById<TextView>(R.id.textDueDate)
    private val mImageView = itemView.findViewById<ImageView>(R.id.imageTask)

    fun bindData(
        taskEntity: TaskEntity,
        listenner: OnTaskListFragmentInteractionListenner
    ) {
        mTextDescription.text = taskEntity.description
        mTextPriority.text = PriorityCacheConstants.getPriorityDescription(taskEntity.priorityId)
        mTextDueDate.text = taskEntity.dueDate

        /** Evento de click para cada task */
        mTextDescription.setOnClickListener {
            listenner.onListClick(taskEntity.id)
        }

        /** Evento de remoção de task */
        mTextDescription.setOnLongClickListener {
            showConfirmDialog(taskEntity, listenner)
            true
        }

        /** Evento de competar a task */
        mImageView.setOnClickListener {
            if (taskEntity.complete) {
                listenner.onUnCompleteTaskClick(taskEntity.id)
            } else {
                listenner.onCompleteTaskClick(taskEntity.id)
            }
        }

        /** Tratamento de tasks completas */
        if (taskEntity.complete) {
            mTextDescription.setTextColor(Color.GREEN)
        }
    }

    /** Dialog de confirmação de remoção de task*/
    private fun showConfirmDialog(
        taskEntity: TaskEntity,
        listenner: OnTaskListFragmentInteractionListenner
    ) {
        AlertDialog.Builder(context)
            .setTitle(R.string.task_remove_confirm)
            .setMessage("${R.string.task_remove_message} ${taskEntity.description}?")
            .setPositiveButton(R.string.remove) { dialog, which ->
                listenner.onDeleteClick(
                    taskEntity.id
                )
            }
            .setNegativeButton(R.string.cancel, null).show()
    }

}