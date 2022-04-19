package com.zlucas2k.mytask.di

import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.repository.TaskRepository
import com.zlucas2k.mytask.domain.usecases.format.date.FormatDateUseCase
import com.zlucas2k.mytask.domain.usecases.format.date.FormatDateUseCaseImpl
import com.zlucas2k.mytask.domain.usecases.format.time.FormatTimeUseCase
import com.zlucas2k.mytask.domain.usecases.format.time.FormatTimeUseCaseImpl
import com.zlucas2k.mytask.domain.usecases.shedule.cancel.CancelScheduleTaskUseCase
import com.zlucas2k.mytask.domain.usecases.shedule.cancel.CancelScheduleTaskUseCaseImpl
import com.zlucas2k.mytask.domain.usecases.shedule.shedule.ScheduleTaskUseCase
import com.zlucas2k.mytask.domain.usecases.shedule.shedule.ScheduleTaskUseCaseImpl
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
    fun provideSheduleTaskUseCase(workerProvider: WorkerProvider<Task>): ScheduleTaskUseCase {
        return ScheduleTaskUseCaseImpl(workerProvider)
    }

    @Provides
    @Singleton
    fun provideCancelSheduleTaskUseCase(workerProvider: WorkerProvider<Task>): CancelScheduleTaskUseCase {
        return CancelScheduleTaskUseCaseImpl(workerProvider)
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
        scheduleTaskUseCase: ScheduleTaskUseCase,
        cancelScheduleTaskUseCase: CancelScheduleTaskUseCase
    ): SaveTaskUseCase {
        return SaveTaskUseCaseImpl(repository, scheduleTaskUseCase, cancelScheduleTaskUseCase)
    }

    @Provides
    @Singleton
    fun provideDeleteTaskUseCase(
        repository: TaskRepository,
        cancelScheduleTaskUseCase: CancelScheduleTaskUseCase
    ): DeleteTaskUseCase {
        return DeleteTaskUseCaseImpl(repository, cancelScheduleTaskUseCase)
    }

    @Provides
    @Singleton
    fun provideFormatDateUseCase(): FormatDateUseCase {
        return FormatDateUseCaseImpl()
    }

    @Provides
    @Singleton
    fun provideFormatTimeUseCase(): FormatTimeUseCase {
        return FormatTimeUseCaseImpl()
    }
}