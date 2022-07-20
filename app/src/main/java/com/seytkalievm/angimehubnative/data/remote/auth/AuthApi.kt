package com.seytkalievm.angimehubnative.data.remote.auth

import com.seytkalievm.angimehubnative.domain.model.User
import com.seytkalievm.angimehubnative.data.remote.auth.model.UserRegisterRequest
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