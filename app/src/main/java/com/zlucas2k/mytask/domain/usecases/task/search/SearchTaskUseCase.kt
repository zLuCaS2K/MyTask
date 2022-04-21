package com.zlucas2k.mytask.domain.usecases.task.search

import com.zlucas2k.mytask.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface SearchTaskUseCase {

    suspend operator fun invoke(query: String): Flow<List<Task>>
}