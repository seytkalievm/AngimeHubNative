package com.seytkalievm.angimehubnative.models

import com.squareup.moshi.Json

data class Show(
    @field:Json(name = "urlImage") val imageUrl: String,
    @field:Json(name = "urlVideo") val videoUrl: String,
    @field:Json(name = "mediaName") val name: String,
    @field:Json(name = "idVideo") val id: Int,
    @field:Json(name = "type") val type: Int,
    @field:Json(name = "views") val views: Int,
    @field:Json(name = "artist") val artist: ArtistPreview
)