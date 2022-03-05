package com.zlucas2k.mytask.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.zlucas2k.mytask.presentation.common.theme.MyTaskTheme
import com.zlucas2k.mytask.presentation.home.components.HomeAddFAB
import com.zlucas2k.mytask.presentation.home.components.HomeTaskCard
import com.zlucas2k.mytask.presentation.home.components.HomeTopAppBar

@Composable
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
        LazyColumn {
            items(5) {
                HomeTaskCard()
            }
        }
    }
}

@Composable
@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    MyTaskTheme {
        HomeScreen()
    }
}