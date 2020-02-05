package com.antonkrasov.cvapp.di

import com.antonkrasov.cvapp.threading.BaseSchedulerProvider
import com.antonkrasov.cvapp.threading.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ThreadingModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideScheduleProvider() : BaseSchedulerProvider {
        return SchedulerProvider()
    }

}