package com.seytkalievm.angimehubnative.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.ui.auth.login.LoginFragment
import com.seytkalievm.angimehubnative.ui.auth.register.RegisterFragment
import com.seytkalievm.angimehubnative.ui.main.SessionActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@AndroidEntryPoint
@ActivityScoped
class AuthActivity : AppCompatActivity() {

    @Inject
    lateinit var registerFragment: RegisterFragment

    @Inject
    lateinit var loginFragment: LoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportFragmentManager.beginTransaction()
            .add(R.id.AuthFragmentContainer, registerFragment)
            .add(R.id.AuthFragmentContainer, loginFragment)
            .attach(loginFragment)
            .show(loginFragment)
            .commit()

    }

    fun goToRegister(){
        supportFragmentManager.beginTransaction()
            .detach(loginFragment)
            .attach(registerFragment)
            .show(registerFragment)
            .commit()
    }

    fun goToLogin(){
        supportFragmentManager.beginTransaction()
            .detach(registerFragment)
            .attach(loginFragment)
            .show(loginFragment)
            .commit()
    }

    fun startSession(){
        val intent = Intent(this, SessionActivity::class.java)
        startActivity(intent)
        finish()
    }
}