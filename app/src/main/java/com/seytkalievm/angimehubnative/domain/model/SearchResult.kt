package com.seytkalievm.angimehubnative.domain.model

import com.squareup.moshi.Json

data class SearchResult(
    @Json(name = "artist")val artist: ArtistPreview,
    @Json(name = "standups")val standUps: ShowPreview,
    @Json(name = "podcasts")val podcasts: ShowPreview,
)