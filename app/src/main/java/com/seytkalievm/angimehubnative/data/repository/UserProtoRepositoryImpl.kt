package com.seytkalievm.angimehubnative.data.repository

import android.content.Context
import androidx.datastore.dataStore
import com.seytkalievm.angimehubnative.data.local.UserSerializer
import com.seytkalievm.angimehubnative.domain.model.User
import com.seytkalievm.angimehubnative.domain.repository.UserProtoRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private const val USER_PREFERENCES_NAME = "user_preferences"

private val Context.dataStore by dataStore(
    fileName = USER_PREFERENCES_NAME,
    serializer = UserSerializer
)

class UserProtoRepositoryImpl @Inject constructor(context: Context): UserProtoRepository {

    private val dataStore = context.dataStore
    private lateinit var _token: String


    override
    fun getToken(): String{
        return _token
    }

    override
    suspend fun getUser(): User {
        val user = dataStore.data.first()
        _token = user.token
        return user
    }

    override
    suspend fun setUser(user: User) {
        dataStore.updateData {
            it.copy(
                token = user.token,
                firstName = user.firstName,
                secondName = user.secondName,
                email = user.email,
                role = user.role,
            )
        }
    }

    override
    suspend fun deleteUser(){
        dataStore.updateData {
            it.copy(
                token = "",
                firstName = "",
                secondName = "",
                email = "",
                role = "",
            )
        }
    }

}