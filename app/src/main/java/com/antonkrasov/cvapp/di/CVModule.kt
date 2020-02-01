package com.antonkrasov.cvapp.di

import androidx.lifecycle.ViewModel
import com.antonkrasov.cvapp.ui.main.CVFragment
import com.antonkrasov.cvapp.ui.main.CVViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class CVModule {

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun cvFragment(): CVFragment

    @Binds
    @IntoMap
    @ViewModelKey(CVViewModel::class)
    abstract fun bindViewModel(cvViewModel: CVViewModel): ViewModel

}