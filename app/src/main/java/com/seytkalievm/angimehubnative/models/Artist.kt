package com.seytkalievm.angimehubnative.models

import com.squareup.moshi.Json

data class Artist(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "firstName") val firstName: String,
    @field:Json(name = "artistSecondName") val secondName: String,
    @field:Json(name = "profileImage") val profileImage: String,
    @field:Json(name = "standUps") val standUps: List<ShowPreview>,
    @field:Json(name = "podcasts") val podcasts: List<ShowPreview>,
)