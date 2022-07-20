package com.seytkalievm.angimehubnative.data.repository

import com.seytkalievm.angimehubnative.domain.model.User
import com.seytkalievm.angimehubnative.data.remote.auth.AuthApi
import com.seytkalievm.angimehubnative.domain.repository.AuthRepository
import com.seytkalievm.angimehubnative.data.remote.auth.model.UserLoginRequest
import com.seytkalievm.angimehubnative.data.remote.auth.model.UserRegisterRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject
constructor(private val authApi: AuthApi): AuthRepository {

    override suspend fun login(credentials: UserLoginRequest): User {
        val token =  authApi.login(
            email = credentials.email,
            password= credentials.password
        )
        val user = authApi.getUserInfo(token)
        user.setToken(token)
        return user
    }

    override suspend fun register(user: UserRegisterRequest): User {
        // Since API does not return user Token after registration
        // in order not to redirect user to login page for better experience
        // user token and all corresponding data is fetched here
        authApi.register(user)
        return login(
            UserLoginRequest(user.email, user.password)
        )
    }


}