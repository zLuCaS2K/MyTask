package com.lucasprojects.mytask.entities.listenner

interface OnTaskListFragmentInteractionListenner {

    fun onListClick(taskId: Int)

    fun onDeleteClick(taskId: Int)

    fun onCompleteTaskClick(taskId: Int)

    fun onUnCompleteTaskClick(taskId: Int)
}