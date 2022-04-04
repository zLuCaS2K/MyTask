package com.zlucas2k.mytask.di

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import com.zlucas2k.mytask.domain.usecases.shedule.cancel.CancelSheduleTaskUseCase
import com.zlucas2k.mytask.domain.usecases.shedule.cancel.CancelSheduleTaskUseCaseImpl
import com.zlucas2k.mytask.domain.usecases.shedule.shedule.SheduleTaskUseCase
import com.zlucas2k.mytask.domain.usecases.shedule.shedule.SheduleTaskUseCaseImpl
import com.zlucas2k.mytask.domain.usecases.task.delete.DeleteTaskUseCase
import com.zlucas2k.mytask.domain.usecases.task.delete.DeleteTaskUseCaseImpl
import com.zlucas2k.mytask.domain.usecases.task.get.GetTaskByIdUseCase
import com.zlucas2k.mytask.domain.usecases.task.get.GetTaskByIdUseCaseImpl
import com.zlucas2k.mytask.domain.usecases.task.get_all.GetAllTaskUseCase
import com.zlucas2k.mytask.domain.usecases.task.get_all.GetAllTaskUseCaseImpl
import com.zlucas2k.mytask.domain.usecases.task.save.SaveTaskUseCase
import com.zlucas2k.mytask.domain.usecases.task.save.SaveTaskUseCaseImpl
import com.zlucas2k.mytask.infrastructure.worker.provider.WorkerProvider
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
    fun provideSheduleTaskUseCase(workerProvider: WorkerProvider<Task>): SheduleTaskUseCase {
        return SheduleTaskUseCaseImpl(workerProvider)
    }

    @Provides
    @Singleton
    fun provideCancelSheduleTaskUseCase(workerProvider: WorkerProvider<Task>): CancelSheduleTaskUseCase {
        return CancelSheduleTaskUseCaseImpl(workerProvider)
    }

    @Provides
    @Singleton
    fun provideGetAllTaskUseCase(repository: TaskRepository): GetAllTaskUseCase {
        return GetAllTaskUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetTaskByIdUseCase(repository: TaskRepository): GetTaskByIdUseCase {
        return GetTaskByIdUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideSaveTaskUseCase(
        repository: TaskRepository,
        sheduleTaskUseCase: SheduleTaskUseCase,
        cancelSheduleTaskUseCase: CancelSheduleTaskUseCase
    ): SaveTaskUseCase {
        return SaveTaskUseCaseImpl(repository, sheduleTaskUseCase, cancelSheduleTaskUseCase)
    }

    @Provides
    @Singleton
    fun provideDeleteTaskUseCase(
        repository: TaskRepository,
        cancelSheduleTaskUseCase: CancelSheduleTaskUseCase
    ): DeleteTaskUseCase {
        return DeleteTaskUseCaseImpl(repository, cancelSheduleTaskUseCase)
    }
}