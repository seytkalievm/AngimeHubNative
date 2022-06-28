package com.seytkalievm.angimehubnative.storage

import android.content.Context
import androidx.datastore.dataStore
import com.seytkalievm.angimehubnative.models.User
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private const val USER_PREFERENCES_NAME = "user_preferences"

private val Context.dataStore by dataStore(
    fileName = USER_PREFERENCES_NAME,
    serializer = UserSerializer
)

class UserProtoRepository @Inject constructor(context: Context){

    private val dataStore = context.dataStore
    private lateinit var _token: String
    val token get() = _token

    suspend fun getUser(): User {
        val user = dataStore.data.first()
        _token = user.token
        return user
    }

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