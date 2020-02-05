package com.antonkrasov.cvapp.di

import android.content.Context
import com.antonkrasov.cvapp.data.api.CVApiService
import com.antonkrasov.cvapp.data.datastore.CVLocalDataStore
import com.antonkrasov.cvapp.data.datastore.CVRemoteDataStore
import com.antonkrasov.cvapp.data.datastore.impl.CVLocalDataStoreImpl
import com.antonkrasov.cvapp.data.datastore.impl.CVRemoteDataStoreImpl
import com.antonkrasov.cvapp.data.repository.CVRepository
import com.antonkrasov.cvapp.data.repository.impl.CVRepositoryImpl
import com.antonkrasov.cvapp.data.storage.AssetsStorage
import com.antonkrasov.cvapp.data.storage.FilesStorage
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
object DataModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideCVLocalDataStore(
        context: Context,
        gson: Gson,
        assetsStorage: AssetsStorage,
        filesStorage: FilesStorage
    ): CVLocalDataStore {
        return CVLocalDataStoreImpl(context, gson, assetsStorage, filesStorage)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideCVRemoteDataStore(cvApiService: CVApiService): CVRemoteDataStore {
        return CVRemoteDataStoreImpl(cvApiService)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideCVRepository(
        cvLocalDataStore: CVLocalDataStore,
        cvRemoteDataStore: CVRemoteDataStore,
        compositeDisposable: CompositeDisposable
    ): CVRepository {
        return CVRepositoryImpl(cvLocalDataStore, cvRemoteDataStore, compositeDisposable)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

}