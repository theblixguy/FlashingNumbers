package com.suyashsrijan.flashingnumbers.main.di

import com.suyashsrijan.flashingnumbers.app.repository.NumbersRepositoryImpl
import com.suyashsrijan.flashingnumbers.main.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideViewModel(repository: NumbersRepositoryImpl) = MainViewModel(repository)
}