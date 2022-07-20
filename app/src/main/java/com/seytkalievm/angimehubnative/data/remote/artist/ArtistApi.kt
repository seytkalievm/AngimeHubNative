package com.seytkalievm.angimehubnative.data.remote.artist

import com.seytkalievm.angimehubnative.domain.model.Artist
import com.seytkalievm.angimehubnative.domain.model.ArtistPreview
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistApi{

    @GET("artist/getInfo")
    suspend fun getArtistInfo(@Query("artistId") artistId: Int): Artist

    @GET("artist/getPopular")
    suspend fun getPopularArtists(): List<ArtistPreview>
}