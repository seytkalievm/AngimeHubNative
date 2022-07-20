package com.seytkalievm.angimehubnative.models

import com.squareup.moshi.Json

data class Show(
    @Json(name = "urlImage") val imageUrl: String,
    @Json(name = "urlVideo") val videoUrl: String,
    @Json(name = "mediaName") val name: String,
    @Json(name = "idVideo") val id: Int,
    @Json(name = "type") val type: Int,
    @Json(name = "views") val views: Int,
    @Json(name = "artist") val artist: ArtistPreview
)