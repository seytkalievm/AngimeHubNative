package com.seytkalievm.angimehubnative.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class User(
    @PrimaryKey var token: String = "",
    @ColumnInfo @field:Json(name = "firstName") val firstName: String,
    @ColumnInfo @field:Json(name = "secondName") val secondName: String,
    @ColumnInfo @field:Json(name = "email") val email: String,
    @ColumnInfo @field:Json(name = "role") val role: String,

)