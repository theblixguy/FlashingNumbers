package com.suyashsrijan.flashingnumbers.app.di

import com.suyashsrijan.flashingnumbers.main.di.MainModule
import com.suyashsrijan.flashingnumbers.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainModule::class)])
    abstract fun bindMainActivity(): MainActivity
}