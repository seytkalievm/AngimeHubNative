package com.seytkalievm.angimehubnative.models

import com.squareup.moshi.Json

data class Artist(
    @Json(name = "id") val id: Int,
    @Json(name = "firstName") val firstName: String,
    @Json(name = "artistSecondName") val secondName: String,
    @Json(name = "profileImage") val profileImage: String,
    @Json(name = "standUps") val standUps: List<ShowPreview>,
    @Json(name = "podcasts") val podcasts: List<ShowPreview>,
)