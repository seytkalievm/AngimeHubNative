package com.seytkalievm.angimehubnative.di

import com.seytkalievm.angimehubnative.di.viewmodel.ViewModelModule
import com.seytkalievm.angimehubnative.ui.auth.login.LoginFragment
import com.seytkalievm.angimehubnative.ui.auth.register.RegisterFragment
import dagger.Component
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory{
        fun create(): AppComponent
    }

    fun inject(fragment: LoginFragment)

    fun inject(fragment: RegisterFragment)
}