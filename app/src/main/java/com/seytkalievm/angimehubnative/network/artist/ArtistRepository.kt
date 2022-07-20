package com.seytkalievm.angimehubnative.network.artist

import com.seytkalievm.angimehubnative.models.Artist
import com.seytkalievm.angimehubnative.models.ArtistPreview

interface ArtistRepository {

    suspend fun getPopularArtists(): List<ArtistPreview>

    suspend fun getArtist(id: Int): Artist
}