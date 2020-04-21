package com.lucasprojects.mytask.adapter

import android.graphics.Color
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.card.MaterialCardView
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.entities.TaskEntity
import com.lucasprojects.mytask.entities.listenner.OnTaskListFragmentInteractionListenner
import com.lucasprojects.mytask.repository.cache.PriorityCacheConstants
import com.lucasprojects.mytask.util.Utils

class TaskListAdapter(private val tasklist: List<TaskEntity>, private val listener: OnTaskListFragmentInteractionListenner) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mTextName = itemView.findViewById(R.id.textDescription) as TextView
        private val mTextPriority = itemView.findViewById(R.id.textPriority) as TextView
        private val mTextDueDate = itemView.findViewById(R.id.textDueDate) as TextView
        private val mMaterialCardView = itemView.findViewById(R.id.layoutCardView) as MaterialCardView
        private val mImageView = itemView.findViewById(R.id.imageTask) as LottieAnimationView

        fun bindData(taskEntity: TaskEntity, listenner: OnTaskListFragmentInteractionListenner) {
            
            with(taskEntity){
                mTextName.text = this.name
                mTextPriority.text = PriorityCacheConstants.getPriorityDescription(this.priorityId)
                mTextDueDate.text = this.dueDate
            }
            
            /** Evento de click para cada task */
            mMaterialCardView.setOnClickListener {
                listenner.onListClick(taskEntity.id)
            }

            /** Evento de remoção de task */
            mMaterialCardView.setOnLongClickListener {
                listenner.onShowBottomSheetDialog(taskEntity)
                true
            }

            /** Evento de competar a task */
            mImageView.setOnClickListener {
                if (taskEntity.complete) {
                    mImageView.setAnimation(R.raw.pedding)
                    Handler().postDelayed({
                        listenner.onUnCompleteTaskClick(taskEntity.id)
                        Toast.makeText(itemView.context, itemView.context.getString(R.string.task_todo_toast), Toast.LENGTH_SHORT).show()
                    }, 2500)
                } else {
                    mImageView.setAnimation(R.raw.complete)
                    Handler().postDelayed({
                        listenner.onCompleteTaskClick(taskEntity.id)
                        Toast.makeText(itemView.context, itemView.context.getString(R.string.task_done_toast), Toast.LENGTH_SHORT).show()
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

            Utils.setColorPriority(mTextPriority)
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