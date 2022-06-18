package com.seytkalievm.angimehubnative.network

import com.seytkalievm.angimehubnative.models.NewUser
import com.seytkalievm.angimehubnative.models.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BaseApi {


    @GET("user/login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ):String

    @POST("user/register")
    suspend fun register(@Body user: NewUser)

    @GET("user/info")
    suspend fun getUserInfo(@Query("token") token: String): User
}