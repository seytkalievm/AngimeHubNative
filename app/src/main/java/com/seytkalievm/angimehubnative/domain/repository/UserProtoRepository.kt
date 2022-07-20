package com.seytkalievm.angimehubnative.domain.repository

import com.seytkalievm.angimehubnative.domain.model.User

interface UserProtoRepository {

    fun getToken(): String

    suspend fun getUser(): User

    suspend fun setUser(user: User)

    suspend fun deleteUser()

}