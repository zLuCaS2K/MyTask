package com.zlucas2k.mytask.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.zlucas2k.mytask.presentation.common.navigation.model.Screen
import com.zlucas2k.mytask.presentation.home.components.HomeAddFAB
import com.zlucas2k.mytask.presentation.home.components.HomeTaskCard
import com.zlucas2k.mytask.presentation.home.components.HomeTopAppBar
import com.zlucas2k.mytask.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: HomeViewModel = viewModel()) {

    val state = viewModel.state.value

    Scaffold(
        topBar = {
            HomeTopAppBar(
                onClickAbout = {
                    // TODO: Abrir dialog de Sobre
                },
                onClickSettings = {
                    // TODO: Abrir tela de Configurações
                }
            )
        },
        floatingActionButton = {
            HomeAddFAB {
                // TODO: Adicionar Tarefa
            }
        }
    ) {
        LazyColumn {
            items(state.tasks) { task ->
                HomeTaskCard(
                    task = task,
                    onEditTask = {

                    },
                    onDeleteTask = {

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(10.dp)
                        .clickable {
                            navHostController.navigate(Screen.TaskScreen.route + "?id={id}")
                        }
                )
            }
        }
    }
}