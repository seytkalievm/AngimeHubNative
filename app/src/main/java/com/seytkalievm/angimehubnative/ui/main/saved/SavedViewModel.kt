package com.seytkalievm.angimehubnative.ui.main.saved

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seytkalievm.angimehubnative.MyApplication
import com.seytkalievm.angimehubnative.models.ShowPreview
import com.seytkalievm.angimehubnative.network.BaseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SavedViewModel"

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val api: BaseApi,
    private val token:String,
): ViewModel() {

    private val _error = MutableLiveData(0)
    val error: LiveData<Int> get() = _error

    private val _shows = MutableLiveData<List<ShowPreview>>()
    val shows: LiveData<List<ShowPreview>> get() = _shows


    init{
        viewModelScope.launch {
            try{
                val shows = api.getFavorites(token)
                _shows.postValue(shows)
            } catch (e: Exception){
                Log.i( TAG, ": ${e.message}")
            }

        }
    }
}