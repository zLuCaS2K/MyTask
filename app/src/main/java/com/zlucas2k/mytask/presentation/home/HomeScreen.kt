package com.zlucas2k.mytask.presentation.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.home.components.HomeAddFAB
import com.zlucas2k.mytask.presentation.home.components.HomeTopAppBar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen() {
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

    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    MyTaskTheme {
        HomeScreen()
    }
}