package com.zlucas2k.mytask.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.zlucas2k.mytask.presentation.components.widget.WidgetValue
import com.zlucas2k.mytask.presentation.screens.home.common.filter.rememberFilterWidgetState
import com.zlucas2k.mytask.presentation.screens.home.common.search.rememberSearchWidgetState
import com.zlucas2k.mytask.presentation.screens.home.components.card.TaskCard
import com.zlucas2k.mytask.presentation.screens.home.components.filter.TaskFilterSection
import com.zlucas2k.mytask.presentation.screens.home.components.topbar.HomeTopAppBar
import com.zlucas2k.mytask.presentation.screens.home.components.topbar.SearchTopAppBar

@Composable
@ExperimentalMaterialApi
fun HomeScreen(navHostController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState
    val searchWidgetState = rememberSearchWidgetState()
    val filterWidgetState = rememberFilterWidgetState()

    Scaffold(
        topBar = {
            when (searchWidgetState.currentWidgetValue) {
                WidgetValue.Opened -> {
                    SearchTopAppBar(
                        query = searchWidgetState.searchQuery,
                        onQueryChange = { searchQuery ->
                            searchWidgetState.onSearchQueryChange(searchQuery)
                        },
                        onCloseSearchTopAppBar = {
                            searchWidgetState.onSearchWidgetStateChange()
                        }
                    )
                }
                WidgetValue.Closed -> {
                    HomeTopAppBar(
                        onSearchClicked = {
                            searchWidgetState.onSearchWidgetStateChange()
                        },
                        onFilterClicked = {
                            filterWidgetState.onFilterWidgetStateChange()
                        }
                    )
                }
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
                AnimatedVisibility(
                    visible = filterWidgetState.currentWidgetValue == WidgetValue.Opened,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    content = {
                        TaskFilterSection(
                            filter = filterWidgetState.filterQuery,
                            onFilterChange = { filterQuery ->
                                filterWidgetState.onFilterQueryChange(filterQuery)
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )

                HomeTaskListItems(tasks = uiState.tasks) { idTaskClicked ->
                    navHostController.navigate(Screen.EditTaskScreen.route + "?id=$idTaskClicked")
                }
            }
        }
    )
}

@Composable
private fun HomeTaskListItems(tasks: List<TaskView>, onClickTask: (Int) -> Unit) {
    LazyColumn {
        items(tasks) { task ->
            TaskCard(
                task = task,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(10.dp)
                    .clickable { onClickTask(task.id) }
            )
        }
    }
}