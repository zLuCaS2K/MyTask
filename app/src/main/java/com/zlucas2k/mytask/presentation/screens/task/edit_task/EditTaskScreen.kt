package com.zlucas2k.mytask.presentation.screens.task.edit_task

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zlucas2k.mytask.R
import com.zlucas2k.mytask.presentation.components.icon.MyTaskIconButton
import com.zlucas2k.mytask.presentation.components.top_bar.MyTaskTopAppBar
import com.zlucas2k.mytask.presentation.screens.task.edit_task.components.EditTaskForm
import com.zlucas2k.mytask.presentation.screens.task.edit_task.components.rememberEditTaskFormState
import com.zlucas2k.mytask.presentation.screens.task.edit_task.utils.EditTaskScreenEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
@ExperimentalComposeUiApi
fun EditTaskScreen(navController: NavController, viewModel: EditTaskViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val uiState = rememberEditTaskScreenState(editTaskViewModel = viewModel)
    val formState = rememberEditTaskFormState(editTaskViewModel = viewModel)

    LaunchedEffect(key1 = Unit) {
        uiState.uiEvent.collectLatest { uiEvent ->
            when (uiEvent) {
                is EditTaskScreenEvent.EditTaskSuccess -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.edit_sucess_task),
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigateUp()
                }

                is EditTaskScreenEvent.EditTaskFailed -> {
                    uiState.showSnackbar(uiEvent.message)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = uiState.scaffoldState,
        topBar = {
            MyTaskTopAppBar(
                title = stringResource(id = R.string.edit_task),
                navigationIcon = {
                    MyTaskIconButton(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back),
                        onClick = { navController.popBackStack() }
                    )
                },
                actions = {
                    MyTaskIconButton(
                        imageVector = Icons.Filled.Save,
                        contentDescription = stringResource(id = R.string.save_task),
                        onClick = {
                            keyboardController?.hide()
                            formState.onEditTask()
                        }
                    )
                }
            )
        },
        content = {
            EditTaskForm(
                editTaskFormState = formState,
                keyboardController = keyboardController,
                modifier = Modifier.fillMaxSize()
            )
        }
    )
}