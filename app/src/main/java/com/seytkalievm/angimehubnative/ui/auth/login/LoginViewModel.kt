package com.seytkalievm.angimehubnative.ui.auth.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.models.User
import com.seytkalievm.angimehubnative.network.BaseApi
import com.seytkalievm.angimehubnative.storage.UserProtoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import javax.inject.Inject

const val TAG = "LoginViewModel"

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userProtoRepository: UserProtoRepository,
    private val baseApi: BaseApi
):ViewModel() {

    private var email = ""
    private var password = ""
    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> get() = _error

    private val _emptyEmail = MutableLiveData(false)
    val emptyEmail: LiveData<Boolean> get() = _emptyEmail

    private val _emptyPassword = MutableLiveData(false)
    val emptyPassword: LiveData<Boolean> get() = _emptyPassword

    private val _user = MutableLiveData<User?>(null)
    val user: LiveData<User?> get() = _user

    fun credentialsChanged(email: String?, password: String?){
        this.email = email?: this.email
        this.password = password?: this.password
    }

    fun logIn(){
        if (email.isNotEmpty() && password.isNotEmpty()){
            viewModelScope.launch {
                try{
                    //since Login API call returns only token, rest of the data is fetched afterwards
                    val token = baseApi.login(email, password)
                    var user = baseApi.getUserInfo(token)
                    user = user.setToken(token)
                    Log.i(TAG, "logIn user: $user")
                    userProtoRepository.setUser(user)
                    _user.postValue(user)
                } catch (e: HttpException){
                    _error.postValue(R.string.incorrect_credentials)
                }  catch (e: ConnectException){
                    _error.postValue(R.string.connection_error)
                } catch (e: Error){
                    Log.e(TAG, "logIn: $e" )
                    _error.postValue(R.string.unknown_error)
                } catch (e: Exception){
                    Log.e(TAG, "logIn: $e" )
                    _error.postValue(R.string.unknown_error)
                }
            }
        } else {
            when{
                email.isEmpty() -> _emptyEmail.postValue(true)
                password.isEmpty() -> _emptyPassword.postValue(true)
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}