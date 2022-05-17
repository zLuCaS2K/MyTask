package com.zlucas2k.mytask.di

import android.app.Application
import com.zlucas2k.mytask.domain.model.Task
import com.zlucas2k.mytask.domain.providers.StringResourceProvider
import com.zlucas2k.mytask.domain.providers.WorkerProvider
import com.zlucas2k.mytask.infrastructure.providers.string.StringResourceProviderImpl
import com.zlucas2k.mytask.infrastructure.providers.worker.TaskWorkerProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InfrastructureModule {

    @Provides
    @Singleton
    fun provideStringResourceProvider(application: Application): StringResourceProvider {
        return StringResourceProviderImpl(application)
    }

    @Provides
    @Singleton
    fun provideTaskWorkerProvider(application: Application): WorkerProvider<Task> {
        return TaskWorkerProviderImpl(application)
    }
}