package com.zlucas2k.mytask.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zlucas2k.mytask.presentation.common.model.TaskView
import com.zlucas2k.mytask.presentation.common.navigation.model.Screen
import com.zlucas2k.mytask.presentation.home.common.filter.FilterWidgetState
import com.zlucas2k.mytask.presentation.home.common.search.SearchWidgetState
import com.zlucas2k.mytask.presentation.home.components.HomeAddFAB
import com.zlucas2k.mytask.presentation.home.components.HomeTaskCard
import com.zlucas2k.mytask.presentation.home.components.filter.TaskFilterSection
import com.zlucas2k.mytask.presentation.home.components.topbar.HomeDefaultTopAppBar
import com.zlucas2k.mytask.presentation.home.components.topbar.HomeSearchTopAppBar
import com.zlucas2k.mytask.presentation.home.viewmodel.HomeViewModel

@Composable
@ExperimentalMaterialApi
fun HomeScreen(navHostController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState

    LaunchedEffect(key1 = uiState.searchQuery) {
        viewModel.onSearchingState(true)
        viewModel.onSearchTask()
        viewModel.onSearchingState(false)
    }

    Scaffold(
        topBar = {
            when (uiState.searchWidgetState) {
                SearchWidgetState.OPENED -> {
                    HomeSearchTopAppBar(
                        text = uiState.searchQuery,
                        onTextChange = viewModel::onSearchTextChange,
                        onSearchClicked = viewModel::onSearchTask,
                        onCloseClicked = viewModel::onSearchWidgetStateChange
                    )
                }

                SearchWidgetState.CLOSED -> {
                    HomeDefaultTopAppBar(
                        onSearchClicked = viewModel::onSearchWidgetStateChange,
                        onFilterClicked = viewModel::onFilterWidgetStateChange
                    )
                }
            }
        },
        floatingActionButton = {
            HomeAddFAB {
                navHostController.navigate(Screen.TaskScreen.route)
            }
        },
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
                AnimatedVisibility(
                    visible = uiState.filterWidgetState == FilterWidgetState.OPENED,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    content = {
                        TaskFilterSection(
                            filter = uiState.filterQuery,
                            onFilterChange = {
                                viewModel.onFilterOptionChange(it)
                                viewModel.onFilterTask()
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                )

                HomeTaskListItems(tasks = uiState.tasks) { idTaskClicked ->
                    navHostController.navigate(Screen.TaskScreen.route + "?id=$idTaskClicked")
                }
            }
        }
    )
}

@Composable
private fun HomeTaskListItems(tasks: List<TaskView>, onClickTask: (Int) -> Unit) {
    LazyColumn {
        items(tasks) { task ->
            HomeTaskCard(
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