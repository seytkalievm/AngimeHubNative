package com.seytkalievm.angimehubnative.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.ui.auth.login.LoginFragment
import com.seytkalievm.angimehubnative.ui.auth.register.RegisterFragment


class AuthActivity : AppCompatActivity() {

    private val registerFragment = RegisterFragment.newInstance()
    private val loginFragment = LoginFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportFragmentManager.beginTransaction().replace(R.id.AuthFragmentContainer, loginFragment).commitNow()
    }

    fun goToRegister(){
        supportFragmentManager.beginTransaction().replace(R.id.AuthFragmentContainer, registerFragment).commitNow()
    }

    fun goToLogin(){
        supportFragmentManager.beginTransaction().replace(R.id.AuthFragmentContainer, loginFragment).commitNow()

    }
}