package com.zlucas2k.mytask.di

import com.zlucas2k.mytask.domain.repository.TaskRepository
import com.zlucas2k.mytask.domain.usecases.shedule.CancelSheduleTaskUseCase
import com.zlucas2k.mytask.domain.usecases.shedule.SheduleTaskUseCase
import com.zlucas2k.mytask.domain.usecases.task.DeleteTaskUseCase
import com.zlucas2k.mytask.domain.usecases.task.GetAllTaskUseCase
import com.zlucas2k.mytask.domain.usecases.task.GetTaskByIdUseCase
import com.zlucas2k.mytask.domain.usecases.task.SaveTaskUseCase
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
    fun provideSaveTaskUseCase(repository: TaskRepository): SaveTaskUseCase {
        return SaveTaskUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteTaskUseCase(repository: TaskRepository): DeleteTaskUseCase {
        return DeleteTaskUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSheduleTaskUseCase(workerProvider: TaskWorkerProvider): SheduleTaskUseCase {
        return SheduleTaskUseCase(workerProvider)
    }

    @Provides
    @Singleton
    fun provideCancelSheduleTaskUseCase(workerProvider: TaskWorkerProvider): CancelSheduleTaskUseCase {
        return CancelSheduleTaskUseCase(workerProvider)
    }
}