package com.zlucas2k.mytask.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zlucas2k.mytask.presentation.common.navigation.model.Screen
import com.zlucas2k.mytask.presentation.home.components.HomeAddFAB
import com.zlucas2k.mytask.presentation.home.components.HomeTaskCard
import com.zlucas2k.mytask.presentation.home.components.HomeTopAppBar
import com.zlucas2k.mytask.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val state = viewModel.state.value

    Scaffold(
        topBar = {
            HomeTopAppBar(
                onClickSettings = {
                    // TODO: Abrir tela de Configurações
                },
                onClickAbout = {
                    // TODO: Abrir dialog de Sobre
                }
            )
        },
        floatingActionButton = {
            HomeAddFAB {
                navHostController.navigate(Screen.TaskScreen.route)
            }
        }
    ) {
        LazyColumn {
            items(state.tasks) { task ->
                HomeTaskCard(
                    task = task,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(10.dp)
                        .clickable {
                            navHostController.navigate(Screen.TaskScreen.route + "?id=${task.id}")
                        }
                )
            }
        }
    }
}