package com.seytkalievm.angimehubnative.ui.auth.register

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

val TAG = "RegistrationViewModel"

class RegisterViewModel @Inject constructor(): ViewModel() {
    private var firstName = ""
    private var secondName = ""
    private var email = ""
    private var password = ""
    private var confPassword = ""

    init {
        Log.i(TAG,this.toString())
    }
    fun credentialsChanged(
        firstName: String? = null,
        secondName: String? = null,
        email: String? = null,
        password: String? = null,
        confPassword: String? = null
    ){
        this.firstName = firstName?: this.firstName
        this.secondName = secondName?: this.secondName
        this.email = email?: this.email
        this.password = password?: this.password
        this.confPassword = confPassword?: this.confPassword
    }

    fun register(){
        if (firstName.isNotEmpty() && secondName.isNotEmpty() && email.isNotEmpty()){
            if (password.isNotEmpty() && confPassword == password){
                //TODO - add API call
                Log.i(TAG, "$firstName, $secondName, $email, $password")
            } else {
                //TODO - show passwords Don't match error
                Log.i(TAG, "passwords don't match")
            }
        } else {
            //TODO - prompt user to fill missing fields
        }
    }

}