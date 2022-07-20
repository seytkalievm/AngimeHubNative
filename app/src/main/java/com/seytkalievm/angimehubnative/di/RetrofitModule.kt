package com.seytkalievm.angimehubnative.di


import com.seytkalievm.angimehubnative.network.artist.ArtistApi
import com.seytkalievm.angimehubnative.network.auth.AuthApi
import com.seytkalievm.angimehubnative.network.shows.ShowsApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BASIC)
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    @Singleton
    @Provides
    fun provideMoshi():Moshi{
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthApi(moshi: Moshi): AuthApi{
        return Retrofit.Builder()
            .baseUrl("http://35.246.32.45:80/user/")
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .client(client)
            .build()
            .create(AuthApi::class.java)
    }

    @Singleton
    @Provides
    fun provideShowsApi(moshi: Moshi): ShowsApi{
        return Retrofit.Builder()
            .baseUrl("http://35.246.32.45:80/")
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .client(client)
            .build()
            .create(ShowsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideArtistApi(moshi: Moshi): ArtistApi{
        return Retrofit.Builder()
            .baseUrl("http://35.246.32.45:80/")
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .client(client)
            .build()
            .create(ArtistApi::class.java)
    }
}