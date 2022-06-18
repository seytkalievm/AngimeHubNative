package com.seytkalievm.angimehubnative.domain

import androidx.lifecycle.LiveData
import com.seytkalievm.angimehubnative.models.User
import com.seytkalievm.angimehubnative.storage.UserDao
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor(private val userDao: UserDao) {

    suspend fun getUser(): User? {
        return userDao.getUser()
    }

    suspend fun addUser(user: User){
        userDao.insertUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

}