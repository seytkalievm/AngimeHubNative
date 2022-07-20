package com.seytkalievm.angimehubnative.ui.main.standup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seytkalievm.angimehubnative.models.ArtistPreview
import com.seytkalievm.angimehubnative.models.ShowPreview
import com.seytkalievm.angimehubnative.network.BaseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandUpViewModel @Inject constructor(private val api: BaseApi): ViewModel() {

    private val _error = MutableLiveData(0)
    val error: LiveData<Int> get() = _error

    private val _shows = MutableLiveData<List<ShowPreview>>()
    val shows: LiveData<List<ShowPreview>> get() = _shows

    private val _artist = MutableLiveData<List<ArtistPreview>>()
    val artist: LiveData<List<ArtistPreview>> get() = _artist

    init{
        viewModelScope.launch {
            try{
                val shows = api.getPopularStandUps()
                val artists = api.getPopularArtists()
                _shows.postValue(shows)
                _artist.postValue(artists)
            } catch (e: Exception){
                Log.i(TAG, ": ${e.message}")
            }

        }
    }

}