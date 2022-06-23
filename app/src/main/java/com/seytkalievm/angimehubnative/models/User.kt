package com.seytkalievm.angimehubnative.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class User(
    @PrimaryKey val token: String = "",
    @ColumnInfo @field:Json(name = "firstName") val firstName: String = "",
    @ColumnInfo @field:Json(name = "secondName") val secondName: String = "",
    @ColumnInfo @field:Json(name = "email") val email: String = "",
    @ColumnInfo @field:Json(name = "role") val role: String = "",
){
    fun setToken(token:String): User{
        return User(token, this.firstName, this.secondName, this.email, this.role)
    }

    fun isNull():Boolean{
        return token=="" && email == ""
    }

    fun getName(): String{
        return "$firstName $secondName"
    }

}