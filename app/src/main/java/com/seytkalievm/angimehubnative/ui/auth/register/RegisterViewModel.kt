package com.seytkalievm.angimehubnative.ui.auth.register

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.models.User
import com.seytkalievm.angimehubnative.network.auth.AuthRepository
import com.seytkalievm.angimehubnative.network.auth.model.UserRegisterRequest
import com.seytkalievm.angimehubnative.storage.UserProtoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.ConnectException
import javax.inject.Inject

const val TAG = "RegistrationViewModel"

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userProtoRepository: UserProtoRepository,
    private val authRepository: AuthRepository,
): ViewModel() {
    private var firstName = ""
    private var secondName = ""
    private var email = ""
    private var password = ""
    private var confPassword = ""

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int> get() = _error

    private val _formState = MutableLiveData(RegisterFormState())
    val formState: LiveData<RegisterFormState> get() = _formState
    private var isFormValid = false

    private val _user = MutableLiveData<User?>(null)
    val user: LiveData<User?> get() = _user

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
            viewModelScope.launch {
                try{
                    val user = authRepository
                        .register(UserRegisterRequest(firstName, secondName, email, password))
                    Log.i(TAG, "Register user: $user")
                    userProtoRepository.setUser(user)
                    _user.postValue(user)

                } catch (e: HttpException){
                    _error.postValue(R.string.user_already_exist)
                } catch (e: ConnectException){
                    _error.postValue(R.string.connection_error)
                } catch (e:Exception){
                    Log.e(TAG, "register: $e")
                    _error.postValue(R.string.unknown_error)
                } catch (e: Error){
                    Log.e(TAG, "Login: $e")
                    _error.postValue(R.string.unknown_error)
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