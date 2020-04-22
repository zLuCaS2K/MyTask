package com.lucasprojects.mytask.entities.listenner

import com.lucasprojects.mytask.entities.TaskEntity

interface OnTaskListFragmentInteractionListenner {

    fun onListClick(taskId: Int)

    fun onDeleteClick(taskId: Int)

    fun onEditClick(taskId: Int)

    fun onCompleteTaskClick(taskId: Int)

    fun onUnCompleteTaskClick(taskId: Int)

    fun onShowBottomSheetDialog(taskEntity: TaskEntity)
}