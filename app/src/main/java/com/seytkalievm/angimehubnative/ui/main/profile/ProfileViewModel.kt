package com.seytkalievm.angimehubnative.ui.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seytkalievm.angimehubnative.models.User
import com.seytkalievm.angimehubnative.storage.UserProtoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val userRepo: UserProtoRepository): ViewModel() {
    lateinit var user: User

    init {
        viewModelScope.launch {
            user = userRepo.getUser().first()
        }
    }

    fun logout(){
        viewModelScope.launch {
            userRepo.deleteUser()
        }
    }
}