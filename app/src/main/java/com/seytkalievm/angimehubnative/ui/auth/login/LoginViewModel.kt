package com.seytkalievm.angimehubnative.ui.auth.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

const val TAG = "LoginViewModel"

class LoginViewModel @Inject constructor():ViewModel() {

    private var email = ""
    private var password = ""
    private val _canLogin = MutableLiveData<Boolean>(false)
    val canLogin: LiveData<Boolean> get() = _canLogin

    init {
        Log.i(TAG,this.toString())
    }

    fun credentialsChanged(email: String?, password: String?){
        this.email = email?: this.email
        this.password = password?: this.password

    }

    fun logIn(){
        if (email.isNotEmpty() && password.isNotEmpty()){
            //TODO - add API call for logging in
            Log.i(TAG, "$email: $password")
        } else {
            //TODO - show error
        }
    }
}