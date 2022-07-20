package com.seytkalievm.angimehubnative.di

import com.seytkalievm.angimehubnative.network.artist.ArtistApi
import com.seytkalievm.angimehubnative.network.artist.ArtistRepository
import com.seytkalievm.angimehubnative.network.artist.ArtistRepositoryImpl
import com.seytkalievm.angimehubnative.network.auth.AuthApi
import com.seytkalievm.angimehubnative.network.auth.AuthRepository
import com.seytkalievm.angimehubnative.network.auth.AuthRepositoryImpl
import com.seytkalievm.angimehubnative.network.shows.ShowsApi
import com.seytkalievm.angimehubnative.network.shows.ShowsRepository
import com.seytkalievm.angimehubnative.network.shows.ShowsRepositoryImpl
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

    @Singleton
    @Provides
    fun provideShowsRepository(api: ShowsApi): ShowsRepository {
        return ShowsRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideArtistRepository(api: ArtistApi): ArtistRepository{
        return ArtistRepositoryImpl(api)
    }
}