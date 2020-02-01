package com.antonkrasov.cvapp.di

import com.antonkrasov.cvapp.BuildConfig
import com.antonkrasov.cvapp.CV_API_BASE_URL
import com.antonkrasov.cvapp.data.CVApiService
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
object NetworkModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        if (!BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        }

        return httpLoggingInterceptor
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(CV_API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideCVApiService(retrofit: Retrofit) : CVApiService {
        return retrofit.create(CVApiService::class.java)
    }

}