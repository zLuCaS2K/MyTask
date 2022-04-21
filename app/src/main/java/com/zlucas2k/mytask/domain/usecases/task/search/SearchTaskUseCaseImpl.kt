package com.zlucas2k.mytask.domain.usecases.task.search

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository
) : SearchTaskUseCase {

    override suspend fun invoke(query: String): Flow<List<Task>> {
        return repository.searchTask(query)
    }
}