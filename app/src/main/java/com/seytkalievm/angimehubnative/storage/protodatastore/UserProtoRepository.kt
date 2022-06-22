package com.seytkalievm.angimehubnative.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.seytkalievm.angimehubnative.models.User
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val USER_PREFERENCES_NAME = "user_preferences"

private val Context.dataStore by dataStore(
    fileName = USER_PREFERENCES_NAME,
    serializer = UserSerializer
)

class UserProtoRepository @Inject constructor(context: Context){

    private val dataStore = context.dataStore

    fun getUser(): Flow<User> {
        return dataStore.data
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