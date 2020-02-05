package com.antonkrasov.cvapp.di

import dagger.Module

@Module(
    includes = [
        DataModule::class,
        NetworkModule::class,
        StorageModule::class,
        ThreadingModule::class
    ]
)
object ApplicationModule