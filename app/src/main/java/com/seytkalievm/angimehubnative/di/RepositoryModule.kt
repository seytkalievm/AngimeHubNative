package com.seytkalievm.angimehubnative.di

import com.seytkalievm.angimehubnative.data.remote.artist.ArtistApi
import com.seytkalievm.angimehubnative.domain.repository.ArtistRepository
import com.seytkalievm.angimehubnative.data.repository.ArtistRepositoryImpl
import com.seytkalievm.angimehubnative.data.remote.auth.AuthApi
import com.seytkalievm.angimehubnative.domain.repository.AuthRepository
import com.seytkalievm.angimehubnative.data.repository.AuthRepositoryImpl
import com.seytkalievm.angimehubnative.data.remote.show.ShowsApi
import com.seytkalievm.angimehubnative.domain.repository.ShowsRepository
import com.seytkalievm.angimehubnative.data.repository.ShowsRepositoryImpl
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
    fun provideAuthRepository(authApi: AuthApi): AuthRepository {
        return AuthRepositoryImpl(authApi)
    }

    @Singleton
    @Provides
    fun provideShowsRepository(api: ShowsApi): ShowsRepository {
        return ShowsRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideArtistRepository(api: ArtistApi): ArtistRepository {
        return ArtistRepositoryImpl(api)
    }
}