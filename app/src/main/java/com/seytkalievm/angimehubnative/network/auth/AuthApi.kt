package com.seytkalievm.angimehubnative.network.auth

import com.seytkalievm.angimehubnative.models.User
import com.seytkalievm.angimehubnative.network.auth.model.UserRegisterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @GET("login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ):String

    @POST("register")
    suspend fun register(@Body user: UserRegisterRequest)

    @GET("info")
    suspend fun getUserInfo(@Query("token") token: String): User

}