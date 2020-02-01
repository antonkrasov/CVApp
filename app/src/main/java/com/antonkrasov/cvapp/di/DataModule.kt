package com.antonkrasov.cvapp.di

import android.content.Context
import com.antonkrasov.cvapp.data.datastore.CVLocalDataStore
import com.antonkrasov.cvapp.data.datastore.CVRemoteDataStore
import com.antonkrasov.cvapp.data.datastore.impl.CVLocalDataStoreImpl
import com.antonkrasov.cvapp.data.datastore.impl.CVRemoteDataStoreImpl
import com.antonkrasov.cvapp.data.repository.CVRepository
import com.antonkrasov.cvapp.data.repository.impl.CVRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideCVLocalDataStore(context: Context): CVLocalDataStore {
        return CVLocalDataStoreImpl(context)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideCVRemoteDataStore(): CVRemoteDataStore {
        return CVRemoteDataStoreImpl()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideCVRepository(
        cvLocalDataStore: CVLocalDataStore,
        cvRemoteDataStore: CVRemoteDataStore
    ): CVRepository {
        return CVRepositoryImpl(cvLocalDataStore, cvRemoteDataStore)
    }

}