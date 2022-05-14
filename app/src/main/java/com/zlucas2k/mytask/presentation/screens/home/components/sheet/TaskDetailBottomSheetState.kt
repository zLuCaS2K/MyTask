package com.zlucas2k.mytask.presentation.screens.home.components.sheet

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import com.zlucas2k.mytask.presentation.common.model.TaskView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
sealed interface TaskDetailBottomSheetState {
    val sheetState: ModalBottomSheetState
    val taskDetail: TaskView
}

@ExperimentalMaterialApi
class TaskDetailBottomSheetStateImpl constructor(
    private val coroutineScope: CoroutineScope,
    override val sheetState: ModalBottomSheetState
) : TaskDetailBottomSheetState {

    private val _taskDetail: MutableState<TaskView> = mutableStateOf(TaskView())
    override val taskDetail: TaskView get() = _taskDetail.value

    fun onShowTaskDetailBottomSheet(task: TaskView) {
        _taskDetail.value = task
        coroutineScope.launch { sheetState.show() }
    }
}

@Composable
@ExperimentalMaterialApi
fun rememberTaskDetailBottomSheetState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    sheetState: ModalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
) = remember {
    TaskDetailBottomSheetStateImpl(
        coroutineScope = coroutineScope,
        sheetState = sheetState
    )
}