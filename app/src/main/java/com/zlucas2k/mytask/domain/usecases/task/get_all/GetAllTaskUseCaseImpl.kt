package com.zlucas2k.mytask.domain.usecases.task.get_all

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository
) : GetAllTaskUseCase {

    override suspend fun invoke(): Flow<List<Task>> {
        return repository.getAllTask()
    }
}