package com.seytkalievm.angimehubnative.network.artist

import com.seytkalievm.angimehubnative.models.Artist
import com.seytkalievm.angimehubnative.models.ArtistPreview
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