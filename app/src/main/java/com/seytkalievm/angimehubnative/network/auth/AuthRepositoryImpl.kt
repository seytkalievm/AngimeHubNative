package com.seytkalievm.angimehubnative.network.auth

import com.seytkalievm.angimehubnative.models.User
import com.seytkalievm.angimehubnative.network.auth.model.UserLoginRequest
import com.seytkalievm.angimehubnative.network.auth.model.UserRegisterRequest
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