package com.seytkalievm.angimehubnative.ui.auth.register

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.models.User
import com.seytkalievm.angimehubnative.network.BaseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

val TAG = "RegistrationViewModel"

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val baseApi: BaseApi
): ViewModel() {
    private var firstName = ""
    private var secondName = ""
    private var email = ""
    private var password = ""
    private var confPassword = ""

    private val _formState = MutableLiveData(RegisterFormState())
    val formState: LiveData<RegisterFormState> get() = _formState
    private var isFormValid = false


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
        validateForm()
        if (isFormValid){
            //TODO - add API call
            viewModelScope.launch {
                try{
                    Log.i(TAG, "$firstName, $secondName, $email, $password")
                    val response = baseApi.register(
                        User(firstName, secondName, email, password)
                    )
                    Log.i(TAG, "register: $response")
                } catch (e: Exception){
                    Log.i(TAG, "register: $e")
                } catch (e: Error){
                    Log.e(TAG, "register: $e", )
                }
            }
        }
    }

    private fun validateForm(){
        isFormValid = isValidName() && isValidEmail() && isValidPassword() && doPasswordsMatch()
    }

    private fun isValidName():Boolean{
        when {
            firstName.length < 2 ->
                _formState.postValue(RegisterFormState(firstNameError = R.string.invalid_first_name))

            secondName.length < 2 ->
                _formState.postValue(RegisterFormState(secondNameError = R.string.invalid_second_name))

            else -> {
                _formState.value?.firstNameError = null
                _formState.value?.secondNameError = null
                return true
            }
        }
        return false
    }

    private fun isValidEmail(): Boolean{
        return if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() || email.isEmpty()){
            _formState.postValue(RegisterFormState(emailError = R.string.invalid_email))
            false
        } else {
            _formState.value?.emailError = null
            true
        }
    }

    private fun isValidPassword(): Boolean{
        when{
            password.length < 6 ->
                _formState.postValue(RegisterFormState(passwordError = R.string.invalid_password_length))

            !password.contains(Regex("^(?=.*?[A-Z])")) ->
                _formState.postValue(RegisterFormState(passwordError = R.string.invalid_password_no_upper))

            !password.contains(Regex("^(?=.*?[a-z])")) ->
                _formState.postValue(RegisterFormState(passwordError = R.string.invalid_password_no_lower))

            !password.contains(Regex("^(?=.*?[0-9])")) ->
                _formState.postValue(RegisterFormState(passwordError = R.string.invalid_password_numeric))
            else -> {
                _formState.postValue(RegisterFormState(passwordError = null))
                return true
            }
        }

        return false
    }

    private fun doPasswordsMatch(): Boolean{
        return if (password != confPassword){
            _formState.postValue(RegisterFormState(confPasswordError = R.string.password_do_not_match))
            false
        } else {
            _formState.value?.confPasswordError = null
            true
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
        Log.i(TAG, "onCleared")
    }
}