package com.seytkalievm.angimehubnative.di

import android.content.Context
import com.seytkalievm.angimehubnative.storage.UserProtoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProtoDataStoreModule {

    @Singleton
    @Provides
    fun provideProtoDataStore(@ApplicationContext context: Context): UserProtoRepository{
        return UserProtoRepository(context)
    }
}