package com.seytkalievm.angimehubnative.models

data class User(
    val firstName: String,
    val secondName: String,
    val email: String,
    var role: String,
    val token: String
)