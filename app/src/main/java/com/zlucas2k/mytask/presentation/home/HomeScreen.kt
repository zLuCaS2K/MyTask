package com.zlucas2k.mytask.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.zlucas2k.mytask.presentation.home.common.SearchWidgetState
import com.zlucas2k.mytask.presentation.home.components.HomeAddFAB
import com.zlucas2k.mytask.presentation.home.components.HomeTaskCard
import com.zlucas2k.mytask.presentation.home.components.HomeTopAppBar
import com.zlucas2k.mytask.presentation.home.viewmodel.HomeViewModel

@Composable
@ExperimentalMaterialApi
fun HomeScreen(navHostController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState
    val searchTextState by viewModel.searchTextState
    val searchWidgetState by viewModel.searchWidgetState

    LaunchedEffect(key1 = searchTextState) {
        viewModel.onSearchingState(true)
        viewModel.onSearchTask(searchTextState)
        viewModel.onSearchingState(false)
    }

    Scaffold(
        topBar = {
            HomeTopAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    viewModel.onTextSearchChange(it)
                },
                onCloseClicked = {
                    viewModel.onSearchWidgetStateChange(SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    viewModel.onSearchTask(searchTextState)
                },
                onSearchTriggered = {
                    viewModel.onSearchWidgetStateChange(SearchWidgetState.OPENED)
                }
            )
        },
        floatingActionButton = {
            HomeAddFAB {
                navHostController.navigate(Screen.TaskScreen.route)
            }
        },
        content = {
            HomeTaskListItems(tasks = uiState.tasks) { idTaskClicked ->
                navHostController.navigate(Screen.TaskScreen.route + "?id=$idTaskClicked")
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