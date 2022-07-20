package com.seytkalievm.angimehubnative.storage

import com.seytkalievm.angimehubnative.models.User

interface UserProtoRepository {

    fun getToken(): String

    suspend fun getUser(): User

    suspend fun setUser(user: User)

    suspend fun deleteUser()

}