package com.seytkalievm.angimehubnative.di

import android.content.Context
import com.seytkalievm.angimehubnative.storage.UserDao
import com.seytkalievm.angimehubnative.storage.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context):UserDatabase{
        return UserDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideUserDao(userDb: UserDatabase): UserDao{
        return userDb.userDao
    }

}