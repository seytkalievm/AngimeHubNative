package com.seytkalievm.angimehubnative.network.shows

import com.seytkalievm.angimehubnative.models.Show
import com.seytkalievm.angimehubnative.models.ShowPreview
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ShowsApi {

    @GET("standup/getPopulars")
    suspend fun getPopularStandUps(): List<ShowPreview>

    @GET("podcast/getPopulars")
    suspend fun getPopularPodcasts(): List<ShowPreview>

    @GET("media/videoFullInfo")
    suspend fun getFullShowInfo(@Query("videoId") videoId: Int): Show

    @POST("user/favourite/add")
    suspend fun addToFavourites(
        @Query("token") token: String,
        @Query("videoId") videoId: Int
    )

    @POST("user/favourite/delete")
    suspend fun deleteFromFavourites(
        @Query("token") token: String,
        @Query("videoId") videoId: Int
    )

    @GET("user/favourite/get")
    suspend fun getFavorites(@Query("token") token: String): List<ShowPreview>
}