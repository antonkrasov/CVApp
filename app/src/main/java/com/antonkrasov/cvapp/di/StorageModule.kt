package com.antonkrasov.cvapp.di

import android.content.Context
import com.antonkrasov.cvapp.BuildConfig
import com.antonkrasov.cvapp.CV_API_BASE_URL
import com.antonkrasov.cvapp.data.CVApiService
import com.antonkrasov.cvapp.data.storage.AssetsStorage
import com.antonkrasov.cvapp.data.storage.FilesStorage
import com.antonkrasov.cvapp.data.storage.impl.AssetsStorageImpl
import com.antonkrasov.cvapp.data.storage.impl.FilesStorageImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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