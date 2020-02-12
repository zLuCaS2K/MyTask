package com.lucasprojects.mytask.holder

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.constants.TaskConstants
import com.lucasprojects.mytask.entities.TaskEntity
import com.lucasprojects.mytask.entities.listenner.OnTaskListFragmentInteractionListenner
import com.lucasprojects.mytask.repository.cache.PriorityCacheConstants

class TaskViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {

    private val mTextDescription = itemView.findViewById<TextView>(R.id.textDescription)
    private val mTextPriority = itemView.findViewById<TextView>(R.id.textPriority)
    private val mTextDueDate = itemView.findViewById<TextView>(R.id.textDueDate)
    private val mImageView = itemView.findViewById<ImageView>(R.id.imageTask)
    private val mConstraintLayout = itemView.findViewById<ConstraintLayout>(R.id.layoutContraintOne)

    fun bindData(
        taskEntity: TaskEntity,
        listenner: OnTaskListFragmentInteractionListenner
    ) {
        mTextDescription.text = taskEntity.description
        mTextPriority.text = PriorityCacheConstants.getPriorityDescription(taskEntity.priorityId)
        mTextDueDate.text = taskEntity.dueDate

        /** Evento de click para cada task */
        mConstraintLayout.setOnClickListener {
            listenner.onListClick(taskEntity.id)
        }

        /** Evento de remoção de task */
        mConstraintLayout.setOnLongClickListener {
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
            mImageView.setImageResource(R.drawable.sucess)
            mTextDescription.setTextColor(Color.GREEN)
        } else {
            mImageView.setImageResource(R.drawable.pedding)
        }

        /** Definindo cor da prioridade de acordo com sua prioridade */
        when (mTextPriority.text) {
            TaskConstants.PRIORITIES.LOW -> mTextPriority.setTextColor(Color.GREEN)
            TaskConstants.PRIORITIES.MEDIUM -> mTextPriority.setTextColor(Color.YELLOW)
            TaskConstants.PRIORITIES.HIGH -> mTextPriority.setTextColor(Color.rgb(255, 165, 0))
            TaskConstants.PRIORITIES.CRITICAL -> mTextPriority.setTextColor(Color.RED)
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