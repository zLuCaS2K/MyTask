package com.lucasprojects.mytask.entities.listenner

interface OnTaskListFragmentInteractionListenner {

    fun onListClick(taskId: Int): Int

    fun onDeleteClick(taskId: Int): Int

    fun onCompleteTaskClick(taskId: Int): Int

    fun onUnCompleteTaskClick(taskId: Int): Int
}