package com.lucasprojects.mytask.entities.listenner

/**
 * Essa interface faz a interligação do TaskListAdapter com o TaskViewHolder do RecyclerView,
 * que lista as tarefas que o usuário possuí.
 * */

interface OnTaskListFragmentInteractionListenner {

    fun onListClick(taskId: Int)

    fun onDeleteClick(taskId: Int)

    fun onCompleteTaskClick(taskId: Int)

    fun onUnCompleteTaskClick(taskId: Int)
}