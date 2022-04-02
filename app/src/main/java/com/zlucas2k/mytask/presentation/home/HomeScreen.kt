package com.zlucas2k.mytask.presentation.home

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.common.model.TaskView
import com.zlucas2k.mytask.presentation.common.navigation.model.Screen
import com.zlucas2k.mytask.presentation.home.components.HomeAddFAB
import com.zlucas2k.mytask.presentation.home.components.HomeTaskCard
import com.zlucas2k.mytask.presentation.home.components.HomeTopAppBar
import com.zlucas2k.mytask.presentation.home.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
@ExperimentalMaterialApi
fun HomeScreen(navHostController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {

    val state = viewModel.state.value
    val coroutineScope = rememberCoroutineScope()
    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetShape = RoundedCornerShape(8.dp),
        sheetContent = {
            ModalBottomSheetContent()
        },
        content = {
            Scaffold(
                topBar = {
                    HomeTopAppBar(
                        onClickAbout = {
                            coroutineScope.launch { bottomState.show() }
                        }
                    )
                },
                floatingActionButton = {
                    HomeAddFAB {
                        navHostController.navigate(Screen.TaskScreen.route)
                    }
                },
                content = {
                    HomeTaskListItems(tasks = state.tasks) { idTaskClicked ->
                        navHostController.navigate(Screen.TaskScreen.route + "?id=$idTaskClicked")
                    }
                }
            )
        }
    )
}

@Composable
private fun ModalBottomSheetContent() {
    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("https://github.com/zLuCaS2K/MyTask")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )

        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onBackground,
        )

        Text(
            text = stringResource(id = R.string.app_description),
            style = MaterialTheme.typography.caption
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
        ) {
            ModalBottomSheetOptions(text = stringResource(id = R.string.source_code)) {
                context.startActivity(intent)
            }

            ModalBottomSheetOptions(text = stringResource(id = R.string.version)) {
                Toast.makeText(context, "1.0", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
private fun ModalBottomSheetOptions(text: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(TextFieldDefaults.MinHeight)
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
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