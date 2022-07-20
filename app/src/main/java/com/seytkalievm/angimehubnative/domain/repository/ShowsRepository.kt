package com.seytkalievm.angimehubnative.domain.repository

import com.seytkalievm.angimehubnative.domain.model.Show
import com.seytkalievm.angimehubnative.domain.model.ShowPreview

interface ShowsRepository {

    suspend fun getPodcasts(): List<ShowPreview>

    suspend fun getStandUps(): List<ShowPreview>

    suspend fun getShowFullInfo(id: Int): Show

    suspend fun getFavourites(token: String): List<ShowPreview>

    suspend fun addToFavourites(token: String, videoId: Int)

    suspend fun deleteFromFavourites(token: String, videoId: Int)

}