package com.lucasprojects.mytask.adapter

import android.graphics.Color
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.card.MaterialCardView
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.constants.TaskConstants
import com.lucasprojects.mytask.entities.TaskEntity
import com.lucasprojects.mytask.entities.listenner.OnTaskListFragmentInteractionListenner
import com.lucasprojects.mytask.repository.cache.PriorityCacheConstants

class TaskListAdapter(private val tasklist: List<TaskEntity>, private val listener: OnTaskListFragmentInteractionListenner) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mTextName = itemView.findViewById(R.id.textDescription) as TextView
        private val mTextPriority = itemView.findViewById(R.id.textPriority) as TextView
        private val mTextDueDate = itemView.findViewById(R.id.textDueDate) as TextView
        private val mMaterialCardView = itemView.findViewById(R.id.layoutCardView) as MaterialCardView
        private val mImageView = itemView.findViewById(R.id.imageTask) as LottieAnimationView

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
                showBottomSheetDialog(taskEntity, listenner)
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

            defineColorPriority(mTextPriority)
        }

        private fun showBottomSheetDialog(taskEntity: TaskEntity, listener: OnTaskListFragmentInteractionListenner) {
            val bottomSheetDialog = BottomSheetDialog(itemView.context, R.style.BottomSheetDialogTheme)
            val bottomSheetView = LayoutInflater.from(itemView.context).inflate(R.layout.layout_bottom_sheet, bottomSheetDialog.findViewById(R.id.bottomSheetContainer))
            val bottomNameTask = bottomSheetView.findViewById(R.id.bottomNameTask) as TextView
            val bottomDueDateTask = bottomSheetView.findViewById(R.id.bottomDueDateTask) as TextView
            val bottomPriorityTask = bottomSheetView.findViewById(R.id.bottomPriorityTask) as TextView
            val containerEditTask = bottomSheetView.findViewById(R.id.containerEditTask) as LinearLayout
            val containerDeleteTask = bottomSheetView.findViewById(R.id.containerDeleteTask) as LinearLayout
            val btnCloseBottom = bottomSheetView.findViewById(R.id.btnCloseBottom) as Button

            with(taskEntity) {
                bottomNameTask.text = this.name
                bottomDueDateTask.text = this.dueDate
                bottomPriorityTask.text = PriorityCacheConstants.getPriorityDescription(taskEntity.priorityId)
            }

            defineColorPriority(bottomPriorityTask)

            containerEditTask.setOnClickListener {
                listener.onListClick(taskEntity.id)
            }

            containerDeleteTask.setOnClickListener {
                showConfirmDialog(taskEntity, listener)
            }

            btnCloseBottom.setOnClickListener {
                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }

        private fun showConfirmDialog(taskEntity: TaskEntity, listenner: OnTaskListFragmentInteractionListenner) {
            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.task_remove_confirm)
                .setMessage("${itemView.context.resources.getString(R.string.task_remove_message)} ${taskEntity.name}?")
                .setPositiveButton(R.string.remove) { _, _ -> listenner.onDeleteClick(taskEntity.id) }
                .setNegativeButton(R.string.cancel, null).show()
        }
    }

    private fun defineColorPriority(textView: TextView) {
        when (textView.text) {
            TaskConstants.PRIORITIES.LOW -> textView.setTextColor(Color.GREEN)
            TaskConstants.PRIORITIES.MEDIUM -> textView.setTextColor(Color.YELLOW)
            TaskConstants.PRIORITIES.HIGH -> textView.setTextColor(Color.rgb(255, 165, 0))
            TaskConstants.PRIORITIES.CRITICAL -> textView.setTextColor(Color.RED)
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