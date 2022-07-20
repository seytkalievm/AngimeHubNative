package com.seytkalievm.angimehubnative.network.auth.model

data class UserLoginRequest(
    val email: String,
    val password: String,
)