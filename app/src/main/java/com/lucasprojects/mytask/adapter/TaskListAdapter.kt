package com.lucasprojects.mytask.adapter

import android.graphics.Color
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.card.MaterialCardView
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.constants.TaskConstants
import com.lucasprojects.mytask.entities.TaskEntity
import com.lucasprojects.mytask.entities.listenner.OnTaskListFragmentInteractionListenner
import com.lucasprojects.mytask.repository.cache.PriorityCacheConstants

class TaskListAdapter(private val tasklist: List<TaskEntity>, private val listener: OnTaskListFragmentInteractionListenner) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mTextName = itemView.findViewById<TextView>(R.id.textDescription)
        private val mTextPriority = itemView.findViewById<TextView>(R.id.textPriority)
        private val mTextDueDate = itemView.findViewById<TextView>(R.id.textDueDate)
        private val mMaterialCardView = itemView.findViewById<MaterialCardView>(R.id.layoutCardView)
        private val mImageView = itemView.findViewById<LottieAnimationView>(R.id.imageTask)

        fun bindData(taskEntity: TaskEntity, listenner: OnTaskListFragmentInteractionListenner) {
            mTextName.text = taskEntity.name
            mTextPriority.text = PriorityCacheConstants.getPriorityDescription(taskEntity.priorityId)
            mTextDueDate.text = taskEntity.dueDate

            /** Evento de click para cada task */
            mMaterialCardView.setOnClickListener {
                listenner.onListClick(taskEntity.id)
            }

            /** Evento de remoção de task */
            mMaterialCardView.setOnLongClickListener {
                showConfirmDialog(taskEntity, listenner)
                true
            }

            /** Evento de competar a task */
            mImageView.setOnClickListener {
                if (taskEntity.complete) {
                    mImageView.setAnimation(R.raw.pedding)
                    Handler().postDelayed({
                        listenner.onUnCompleteTaskClick(taskEntity.id)
                        Toast.makeText(itemView.context, "A Tarefa ficou Pendente!", Toast.LENGTH_SHORT).show()
                    }, 2500)
                } else {
                    mImageView.setAnimation(R.raw.complete)
                    Handler().postDelayed({
                        listenner.onCompleteTaskClick(taskEntity.id)
                        Toast.makeText(itemView.context, "A Tarefa foi Completa!", Toast.LENGTH_SHORT).show()
                    }, 2500)
                }
                mImageView.playAnimation()
            }

            /** Tratamento de tasks completas */
            if (taskEntity.complete) {
                mImageView.setAnimation(R.raw.complete)
                mImageView.playAnimation()
                mTextName.setTextColor(Color.GREEN)
            } else {
                mImageView.setAnimation(R.raw.pedding)
                mImageView.playAnimation()
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
        private fun showConfirmDialog(taskEntity: TaskEntity, listenner: OnTaskListFragmentInteractionListenner) {
            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.task_remove_confirm)
                .setMessage("${itemView.context.resources.getString(R.string.task_remove_message)} ${taskEntity.name}?")
                .setPositiveButton(R.string.remove) { _, _ -> listenner.onDeleteClick(taskEntity.id) }
                .setNegativeButton(R.string.cancel, null).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_task_list, parent, false))
    }

    override fun getItemCount() = tasklist.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task: TaskEntity = tasklist[position]
        holder.bindData(task, listener)
    }
}