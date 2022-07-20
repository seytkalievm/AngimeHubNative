@file:Suppress("BlockingMethodInNonBlockingContext")

package com.seytkalievm.angimehubnative.data.local

import android.util.Log
import androidx.datastore.core.Serializer
import com.seytkalievm.angimehubnative.domain.model.User
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

const val TAG = "UserSerializer"
object UserSerializer: Serializer<User>{

    override val defaultValue: User
        get() = User()

    override suspend fun readFrom(input: InputStream): User {
       return try{
           Json.decodeFromString(
               User.serializer(),
               input.readBytes().decodeToString()
           )
       } catch (e: SerializationException){
           Log.e(TAG, "readFrom: ${e.message}")
           defaultValue
       }
    }

    override suspend fun writeTo(t: User, output: OutputStream) {
        output.write(
            Json.encodeToString(
                User.serializer(),
                t
            ).encodeToByteArray()
        )
    }

}