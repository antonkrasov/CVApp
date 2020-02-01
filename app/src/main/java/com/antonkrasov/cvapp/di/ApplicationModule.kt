package com.antonkrasov.cvapp.di

import android.content.Context
import com.antonkrasov.cvapp.data.datastore.CVLocalDataStore
import com.antonkrasov.cvapp.data.datastore.impl.CVLocalDataStoreImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        DataModule::class,
        NetworkModule::class
    ]
)
object ApplicationModule