package com.lucasprojects.mytask.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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

            /** Tratamento de tasks completas */
            if (taskEntity.complete) {
                mTextName.setTextColor(Color.GREEN)
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