package com.seytkalievm.angimehubnative.network

import com.seytkalievm.angimehubnative.models.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BaseApi {


    @GET("user/login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ):String

    @POST("user/register")
    suspend fun register(@Body user: NewUser)

    @GET("user/info")
    suspend fun getUserInfo(@Query("token") token: String): User

    @GET("artist/getPopular")
    suspend fun getPopularArtists():List<ArtistPreview>

    @GET("standup/getPopulars")
    suspend fun getPopularStandUps(): List<ShowPreview>

    @GET("podcasts/getPopulars")
    suspend fun getPopularPodcasts(): List<ShowPreview>

    @GET("artist/getInfo")
    suspend fun getArtistInfo(@Query("artistId") artistId: Int): Artist

    @GET("media/videoFullInfo")
    suspend fun getFullShowInfo(@Query("videoId") videoId: Int): Show

    @GET("user/recordings")
    suspend fun getFavorites(@Query("token") token: String): List<ShowPreview>

    @GET("search")
    suspend fun search(@Query("searchValue") searchValue: String): SearchResult
}