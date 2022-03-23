package com.zlucas2k.mytask.di

import android.app.Application
import com.zlucas2k.mytask.infrastructure.worker.provider.TaskWorkerProvider
import com.zlucas2k.mytask.infrastructure.worker.provider.TaskWorkerProviderImpl
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
    fun provideTaskWorkerProvider(application: Application): TaskWorkerProvider {
        return TaskWorkerProviderImpl(application)
    }
}