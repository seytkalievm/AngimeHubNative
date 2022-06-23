package com.seytkalievm.angimehubnative.models

import com.squareup.moshi.Json

data class SearchResult(
    @field:Json(name = "artist")val artist: ArtistPreview,
    @field:Json(name = "standups")val standUps: ShowPreview,
    @field:Json(name = "podcasts")val podcasts: ShowPreview,
)