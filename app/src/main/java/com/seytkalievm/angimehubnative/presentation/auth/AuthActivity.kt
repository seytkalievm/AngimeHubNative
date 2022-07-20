package com.seytkalievm.angimehubnative.presentation.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.presentation.session.SessionActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped


@AndroidEntryPoint
@ActivityScoped
class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    fun startSession(){
        val intent = Intent(this, SessionActivity::class.java)
        startActivity(intent)
        finish()
    }
}