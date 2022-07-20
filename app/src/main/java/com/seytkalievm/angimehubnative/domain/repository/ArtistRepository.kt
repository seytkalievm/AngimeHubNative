package com.seytkalievm.angimehubnative.domain.repository

import com.seytkalievm.angimehubnative.domain.model.Artist
import com.seytkalievm.angimehubnative.domain.model.ArtistPreview

interface ArtistRepository {

    suspend fun getPopularArtists(): List<ArtistPreview>

    suspend fun getArtist(id: Int): Artist
}