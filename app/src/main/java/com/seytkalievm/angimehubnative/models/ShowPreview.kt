package com.seytkalievm.angimehubnative.models

import com.squareup.moshi.Json


data class ShowPreview (
    @field:Json(name = "urlImage") val imageUrl: String,
    @field:Json(name = "urlVideo") val videoUrl: String,
    @field:Json(name = "mediaName") val name: String,
    @field:Json(name = "idVideo") val id: Int,
    @field:Json(name = "artistFirstName") val artistFirstName: String,
    @field:Json(name = "artistSecondName") val artistSecondName: String,
)