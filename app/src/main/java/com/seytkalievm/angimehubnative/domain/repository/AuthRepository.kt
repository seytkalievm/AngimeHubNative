package com.seytkalievm.angimehubnative.domain.repository

import com.seytkalievm.angimehubnative.domain.model.User
import com.seytkalievm.angimehubnative.data.remote.auth.model.UserLoginRequest
import com.seytkalievm.angimehubnative.data.remote.auth.model.UserRegisterRequest

interface AuthRepository {

    suspend fun login(credentials: UserLoginRequest): User

    suspend fun register(user: UserRegisterRequest): User

}