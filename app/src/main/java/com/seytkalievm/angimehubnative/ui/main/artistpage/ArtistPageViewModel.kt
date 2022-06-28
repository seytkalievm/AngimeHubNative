package com.seytkalievm.angimehubnative.ui.main.artistpage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seytkalievm.angimehubnative.models.Artist
import com.seytkalievm.angimehubnative.models.Show
import com.seytkalievm.angimehubnative.models.ShowPreview
import com.seytkalievm.angimehubnative.network.BaseApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ArtistPageViewModel"

@HiltViewModel
class ArtistPageViewModel @Inject constructor (private val api: BaseApi): ViewModel() {
    private val _artist = MutableLiveData<Artist>()
    val artist: LiveData<Artist> get() = _artist

    private val _popularStandUps = MutableLiveData<List<ShowPreview>>()
    val popularStandUps: LiveData<List<ShowPreview>> get()  = _popularStandUps

    private var _popularPodcasts = MutableLiveData<List<ShowPreview>>()
    val popularPodcasts: LiveData<List<ShowPreview>> get() = _popularPodcasts

    fun getArtist(id: Int){
        viewModelScope.launch {
            try{
                val artist = api.getArtistInfo(id)
                val podcasts = artist.podcasts.take(3)
                val standUps = artist.standUps.take(3)
                _artist.postValue(artist)
                _popularStandUps.postValue(standUps)
                _popularPodcasts.postValue(podcasts)
            } catch (e: Exception){
                Log.i(TAG, ": ${e.message}")
            }
        }
    }

}