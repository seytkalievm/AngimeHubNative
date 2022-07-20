package com.seytkalievm.angimehubnative.network.shows

import com.seytkalievm.angimehubnative.models.Show
import com.seytkalievm.angimehubnative.models.ShowPreview

interface ShowsRepository {

    suspend fun getPodcasts(): List<ShowPreview>

    suspend fun getStandUps(): List<ShowPreview>

    suspend fun getShowFullInfo(id: Int): Show

    suspend fun getFavourites(token: String): List<ShowPreview>

    suspend fun addToFavourites(token: String, videoId: Int)

    suspend fun deleteFromFavourites(token: String, videoId: Int)

}