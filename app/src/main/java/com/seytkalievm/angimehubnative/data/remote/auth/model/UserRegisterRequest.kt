package com.seytkalievm.angimehubnative.data.remote.auth.model

import com.squareup.moshi.Json

data class UserRegisterRequest(
    @Json(name = "firstName") val firstName: String,
    @Json(name = "secondName") val secondName: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
)