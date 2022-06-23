package com.seytkalievm.angimehubnative.models

import com.squareup.moshi.Json

data class ArtistPreview(
    @field:Json(name = "artistId") val Id: Int,
    @field:Json(name = "artistFirstName") val firstName: String,
    @field:Json(name = "artistSecondName") val secondName: String,
    @field:Json(name = "profileImage") val imageUrl: String,
)