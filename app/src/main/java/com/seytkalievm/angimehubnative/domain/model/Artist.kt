package com.seytkalievm.angimehubnative.domain.model

import com.squareup.moshi.Json

data class Artist(
    @Json(name = "artistId") val id: Int,
    @Json(name = "artistFirstName") val firstName: String,
    @Json(name = "artistSecondName") val secondName: String,
    @Json(name = "profileImage") val profileImage: String,
    @Json(name = "standups") val standUps: List<ShowPreview>,
    @Json(name = "podcasts") val podcasts: List<ShowPreview>,
) {
    fun getName(): String{
        return "$firstName $secondName"
    }
}