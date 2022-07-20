package com.seytkalievm.angimehubnative.network.artist

import com.seytkalievm.angimehubnative.models.Artist
import com.seytkalievm.angimehubnative.models.ArtistPreview
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistApi{

    @GET("artist/getInfo")
    suspend fun getArtistInfo(@Query("artistId") artistId: Int): Artist

    @GET("artist/getPopular")
    suspend fun getPopularArtists(): List<ArtistPreview>
}