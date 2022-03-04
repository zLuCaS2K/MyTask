package com.zlucas2k.mytask.di

import com.zlucas2k.mytask.data.database.TaskDatabase
import com.zlucas2k.mytask.data.repository.TaskRepositoryImpl
import com.zlucas2k.mytask.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideTaskRepository(taskDatabase: TaskDatabase): TaskRepository {
        return TaskRepositoryImpl(taskDatabase.taskDAO)
    }
}