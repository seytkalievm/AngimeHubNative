package com.seytkalievm.angimehubnative.data.repository

import com.seytkalievm.angimehubnative.domain.model.Artist
import com.seytkalievm.angimehubnative.domain.model.ArtistPreview
import com.seytkalievm.angimehubnative.data.remote.artist.ArtistApi
import com.seytkalievm.angimehubnative.domain.repository.ArtistRepository
import javax.inject.Inject

class ArtistRepositoryImpl
    @Inject constructor(private val api: ArtistApi): ArtistRepository {
    override suspend fun getArtist(id: Int): Artist {
        return api.getArtistInfo(id)
    }

    override suspend fun getPopularArtists(): List<ArtistPreview> {
        return api.getPopularArtists()
    }
}