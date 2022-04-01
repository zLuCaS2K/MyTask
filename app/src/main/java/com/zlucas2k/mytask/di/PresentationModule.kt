package com.zlucas2k.mytask.di

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
        return GetAllTaskUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetTaskByIdUseCase(repository: TaskRepository): GetTaskByIdUseCase {
        return GetTaskByIdUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideSaveTaskUseCase(repository: TaskRepository): SaveTaskUseCase {
        return SaveTaskUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteTaskUseCase(repository: TaskRepository): DeleteTaskUseCase {
        return DeleteTaskUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideSheduleTaskUseCase(workerProvider: TaskWorkerProvider): SheduleTaskUseCase {
        return SheduleTaskUseCaseImpl(workerProvider)
    }

    @Provides
    @Singleton
    fun provideCancelSheduleTaskUseCase(workerProvider: TaskWorkerProvider): CancelSheduleTaskUseCase {
        return CancelSheduleTaskUseCaseImpl(workerProvider)
    }
}