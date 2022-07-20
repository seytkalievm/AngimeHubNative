package com.seytkalievm.angimehubnative.network.auth

import com.seytkalievm.angimehubnative.models.User
import com.seytkalievm.angimehubnative.network.auth.model.UserLoginRequest
import com.seytkalievm.angimehubnative.network.auth.model.UserRegisterRequest

interface AuthRepository {

    suspend fun login(credentials: UserLoginRequest): User

    suspend fun register(user: UserRegisterRequest): User

}