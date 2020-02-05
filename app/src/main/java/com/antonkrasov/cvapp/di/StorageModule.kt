package com.antonkrasov.cvapp.di

import android.content.Context
import com.antonkrasov.cvapp.data.storage.AssetsStorage
import com.antonkrasov.cvapp.data.storage.FilesStorage
import com.antonkrasov.cvapp.data.storage.impl.AssetsStorageImpl
import com.antonkrasov.cvapp.data.storage.impl.FilesStorageImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object StorageModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideAssetsStorage(context: Context) : AssetsStorage {
        return AssetsStorageImpl(context)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideFilesStorage() : FilesStorage {
        return FilesStorageImpl()
    }

}