package com.seytkalievm.angimehubnative.ui.auth.register

data class RegisterFormState (
    var firstNameError: Int? = null,
    var secondNameError: Int? = null,
    var emailError: Int? = null,
    var passwordError: Int? = null,
    var confPasswordError: Int? = null
    )