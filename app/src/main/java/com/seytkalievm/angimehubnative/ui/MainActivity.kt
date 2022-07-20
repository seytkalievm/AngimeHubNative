package com.seytkalievm.angimehubnative.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.models.User
import com.seytkalievm.angimehubnative.storage.UserProtoRepository
import com.seytkalievm.angimehubnative.ui.auth.AuthActivity
import com.seytkalievm.angimehubnative.ui.main.SessionActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var userProtoRepository: UserProtoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var user: User
        runBlocking {
            user = userProtoRepository.getUser()
        }

        Log.i(TAG, "onCreate: $user")

        intent = when (user.isNull()){
            true -> Intent(this, AuthActivity::class.java)
            else -> Intent(this, SessionActivity::class.java)
        }
        setContentView(R.layout.activity_main)

        startActivity(intent)
        finish()
    }
}