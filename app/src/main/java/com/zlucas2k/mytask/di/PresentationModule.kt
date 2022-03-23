package com.zlucas2k.mytask.di

import com.zlucas2k.mytask.domain.repository.TaskRepository
import com.zlucas2k.mytask.domain.usecases.DeleteTaskUseCase
import com.zlucas2k.mytask.domain.usecases.GetAllTaskUseCase
import com.zlucas2k.mytask.domain.usecases.GetTaskByIdUseCase
import com.zlucas2k.mytask.domain.usecases.SaveTaskUseCase
import com.zlucas2k.mytask.infrastructure.worker.provider.TaskWorkerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    // TODO: Implementar somente uma única dependência em formato de classe com todos os casos de usos.

    @Provides
    @Singleton
    fun provideGetAllTaskUseCase(repository: TaskRepository): GetAllTaskUseCase {
        return GetAllTaskUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetTaskByIdUseCase(repository: TaskRepository): GetTaskByIdUseCase {
        return GetTaskByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSaveTaskUseCase(repository: TaskRepository, taskWorkerProvider: TaskWorkerProvider): SaveTaskUseCase {
        return SaveTaskUseCase(repository, taskWorkerProvider)
    }

    @Provides
    @Singleton
    fun provideDeleteTaskUseCase(repository: TaskRepository, taskWorkerProvider: TaskWorkerProvider): DeleteTaskUseCase {
        return DeleteTaskUseCase(repository, taskWorkerProvider)
    }
}