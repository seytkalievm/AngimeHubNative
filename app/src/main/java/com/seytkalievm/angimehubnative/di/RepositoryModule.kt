package com.seytkalievm.angimehubnative.di

import com.seytkalievm.angimehubnative.network.auth.AuthApi
import com.seytkalievm.angimehubnative.network.auth.AuthRepository
import com.seytkalievm.angimehubnative.network.auth.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(authApi: AuthApi):AuthRepository{
        return AuthRepositoryImpl(authApi)
    }
}