package com.seytkalievm.angimehubnative.models

import com.squareup.moshi.Json

data class NewUser(
    @Json(name = "firstName") val firstName: String,
    @Json(name = "secondName") val secondName: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
)