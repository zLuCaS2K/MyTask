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
    fun provideSaveTaskUseCase(repository: TaskRepository, workerProvider: TaskWorkerProvider): SaveTaskUseCase {
        return SaveTaskUseCase(repository, workerProvider)
    }

    @Provides
    @Singleton
    fun provideDeleteTaskUseCase(repository: TaskRepository, workerProvider: TaskWorkerProvider): DeleteTaskUseCase {
        return DeleteTaskUseCase(repository, workerProvider)
    }
}