package com.antonkrasov.cvapp.di

import android.content.Context
import com.antonkrasov.cvapp.CVApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        CVModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<CVApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

}