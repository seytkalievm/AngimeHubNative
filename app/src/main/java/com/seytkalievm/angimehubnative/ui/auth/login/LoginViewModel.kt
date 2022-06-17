package com.seytkalievm.angimehubnative.ui.auth.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seytkalievm.angimehubnative.network.BaseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "LoginViewModel"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val baseApi: BaseApi
):ViewModel() {

    private var email = ""
    private var password = ""


    fun credentialsChanged(email: String?, password: String?){
        this.email = email?: this.email
        this.password = password?: this.password
    }

    fun logIn(){
        if (email.isNotEmpty() && password.isNotEmpty()){
            //TODO - add API call for logging in
            Log.i(TAG, "logIn: Launching coroutine")
            viewModelScope.launch {
                Log.i(TAG, "logIn: Launched coroutine scope")
                try{
                    Log.i(TAG, "Trying to log in $email: $password")
                    val result = baseApi.login(email, password)
                    Log.i(TAG, "LoggedIn: $result")
                } catch (e: Exception){
                    Log.i(TAG, "logIn: ${e.message}")
                } catch (e: Error){
                    Log.e(TAG, "Login: $e")
                }
            }
        } else {
            //TODO - show error
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}