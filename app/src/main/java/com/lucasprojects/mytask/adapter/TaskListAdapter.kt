package com.lucasprojects.mytask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucasprojects.mytask.R
import com.lucasprojects.mytask.entities.TaskEntity
import com.lucasprojects.mytask.entities.listenner.OnTaskListFragmentInteractionListenner
import com.lucasprojects.mytask.holder.TaskViewHolder

class TaskListAdapter(tasklist: List<TaskEntity>, listener: OnTaskListFragmentInteractionListenner) : RecyclerView.Adapter<TaskViewHolder>() {

    private val mListTaskEntity: List<TaskEntity> = tasklist
    private val listener: OnTaskListFragmentInteractionListenner = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val context = parent.context
        /** Infla o layout da linha e faz uso na listagem */
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.row_task_list, parent, false)

        return TaskViewHolder(view, context)
    }

    /** Metodo que conta o tamanho da lista de tasks */
    override fun getItemCount(): Int {
        return mListTaskEntity.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        /** Obter item da lista */
        val task: TaskEntity = mListTaskEntity[position]
        holder.bindData(task, listener)
    }
}