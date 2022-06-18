package com.seytkalievm.angimehubnative.models

import com.squareup.moshi.Json

data class NewUser(
    @field:Json(name = "firstName") val firstName: String,
    @field:Json(name = "secondName") val secondName: String,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "password") val password: String,
)