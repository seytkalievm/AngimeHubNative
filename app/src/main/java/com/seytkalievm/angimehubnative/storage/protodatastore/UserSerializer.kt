@file:Suppress("BlockingMethodInNonBlockingContext")

package com.seytkalievm.angimehubnative.storage

import android.util.Log
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.seytkalievm.angimehubnative.models.User
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt

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