package com.seytkalievm.angimehubnative.presentation.session.standup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seytkalievm.angimehubnative.domain.model.ArtistPreview
import com.seytkalievm.angimehubnative.domain.model.ShowPreview
import com.seytkalievm.angimehubnative.data.remote.artist.ArtistApi
import com.seytkalievm.angimehubnative.data.remote.show.ShowsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandUpViewModel @Inject constructor(
    private val showsApi: ShowsApi,
    private val artistApi: ArtistApi,
): ViewModel() {

    private val _error = MutableLiveData(0)
    val error: LiveData<Int> get() = _error

    private val _shows = MutableLiveData<List<ShowPreview>>()
    val shows: LiveData<List<ShowPreview>> get() = _shows

    private val _artist = MutableLiveData<List<ArtistPreview>>()
    val artist: LiveData<List<ArtistPreview>> get() = _artist

    init{
        viewModelScope.launch {
            try{
                val shows = showsApi.getPopularStandUps()
                val artists = artistApi.getPopularArtists()
                _shows.postValue(shows)
                _artist.postValue(artists)
            } catch (e: Exception){
                Log.i(TAG, ": ${e.message}")
            }

        }
    }

}