package com.seytkalievm.angimehubnative.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.seytkalievm.angimehubnative.network.BaseApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideGson():Gson{
        return GsonBuilder()
            .setLenient()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): BaseApi{
        return Retrofit.Builder()
            .baseUrl("http://35.246.32.45:80/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(BaseApi::class.java)
    }



}