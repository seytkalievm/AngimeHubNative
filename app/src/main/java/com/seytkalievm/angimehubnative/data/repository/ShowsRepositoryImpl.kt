package com.seytkalievm.angimehubnative.data.repository

import com.seytkalievm.angimehubnative.domain.model.Show
import com.seytkalievm.angimehubnative.domain.model.ShowPreview
import com.seytkalievm.angimehubnative.data.remote.show.ShowsApi
import com.seytkalievm.angimehubnative.domain.repository.ShowsRepository
import javax.inject.Inject

class ShowsRepositoryImpl
    @Inject constructor(private val api: ShowsApi): ShowsRepository {
    override suspend fun getPodcasts(): List<ShowPreview> {
        return api.getPopularPodcasts()
    }

    override suspend fun getStandUps(): List<ShowPreview> {
        return api.getPopularStandUps()
    }

    override suspend fun getShowFullInfo(id: Int): Show {
        return api.getFullShowInfo(id)
    }

    override suspend fun getFavourites(token: String): List<ShowPreview> {
        return api.getFavorites(token)
    }

    override suspend fun addToFavourites(token: String, videoId: Int) {
        api.addToFavourites(token, videoId)
    }

    override suspend fun deleteFromFavourites(token: String, videoId: Int) {
        api.deleteFromFavourites(token, videoId)
    }
}