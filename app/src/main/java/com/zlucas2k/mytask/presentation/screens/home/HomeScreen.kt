package com.zlucas2k.mytask.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.model.TaskView
import com.zlucas2k.mytask.presentation.common.navigation.model.Screen
import com.zlucas2k.mytask.presentation.components.fab.MyTaskFloatingActionButton
import com.zlucas2k.mytask.presentation.screens.home.components.card.TaskCard
import com.zlucas2k.mytask.presentation.screens.home.components.filter.TaskFilterWidget
import com.zlucas2k.mytask.presentation.screens.home.components.filter.rememberFilterWidgetState
import com.zlucas2k.mytask.presentation.screens.home.components.sheet.TaskDetailBottomSheet
import com.zlucas2k.mytask.presentation.screens.home.components.sheet.rememberTaskDetailBottomSheetState
import com.zlucas2k.mytask.presentation.screens.home.components.topbar.home.HomeTopAppBar
import com.zlucas2k.mytask.presentation.screens.home.components.topbar.search.SearchTopAppBar
import com.zlucas2k.mytask.presentation.screens.home.components.topbar.search.rememberSearchWidgetState

@Composable
@ExperimentalMaterialApi
fun HomeScreen(navHostController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState
    val searchWidgetState = rememberSearchWidgetState()
    val filterWidgetState = rememberFilterWidgetState()
    val taskDetailBottomSheetState = rememberTaskDetailBottomSheetState()

    TaskDetailBottomSheet(
        taskDetailBottomSheetState = taskDetailBottomSheetState,
        screenContent = {
            Scaffold(
                topBar = {
                    if (searchWidgetState.isVisible) {
                        SearchTopAppBar(
                            searchWidgetState = searchWidgetState,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        HomeTopAppBar(
                            onSearchClicked = {
                                searchWidgetState.openWidget()
                            },
                            onFilterClicked = {
                                if (filterWidgetState.isVisible) {
                                    filterWidgetState.closeWidget()
                                } else {
                                    filterWidgetState.openWidget()
                                }
                            }
                        )
                    }
                },
                floatingActionButton = {
                    MyTaskFloatingActionButton(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(id = R.string.add_task),
                        onClick = {
                            navHostController.navigate(Screen.AddTaskScreen.route)
                        }
                    )
                },
                content = {
                    Column(modifier = Modifier.fillMaxSize()) {
                        TaskFilterWidget(
                            taskFilterWidgetState = filterWidgetState,
                            modifier = Modifier.fillMaxWidth()
                        )

                        TaskListItems(
                            tasks = uiState.tasks,
                            onClickTask = { taskClicked ->
                                taskDetailBottomSheetState.onShowTaskDetailBottomSheet(taskClicked)
                            },
                            onClickEdit = { id ->
                                navHostController.navigate(Screen.EditTaskScreen.route + "?id=$id")
                            },
                            onClickDelete = { task ->
                                viewModel.onDeleteTask(task)
                            },
                        )
                    }
                }
            )
        }
    )
}

@Composable
private fun TaskListItems(
    tasks: List<TaskView>,
    onClickTask: (TaskView) -> Unit,
    onClickEdit: (Int) -> Unit,
    onClickDelete: (TaskView) -> Unit
) {
    LazyColumn {
        items(tasks) { task ->
            TaskCard(
                task = task,
                onEditClicked = {
                    onClickEdit(task.id)
                },
                onDeleteClicked = {
                    onClickDelete(task)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable { onClickTask(task) },
            )
        }
    }
}