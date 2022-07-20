package com.seytkalievm.angimehubnative.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seytkalievm.angimehubnative.models.User
import com.seytkalievm.angimehubnative.storage.UserProtoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val userRepo: UserProtoRepository): ViewModel() {
    lateinit var user: User
    private val _deleted = MutableLiveData(false)
    val deleted: LiveData<Boolean> get() = _deleted

    init {
        viewModelScope.launch {
            user = userRepo.getUser()
        }
    }

    fun logout(){
        viewModelScope.launch {
            userRepo.deleteUser()
            _deleted.postValue(true)
        }
    }
}