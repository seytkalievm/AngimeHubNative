package com.seytkalievm.angimehubnative.models

import com.squareup.moshi.Json

data class ArtistPreview(
    @Json(name = "artistId") val Id: Int,
    @Json(name = "artistFirstName") val firstName: String,
    @Json(name = "artistSecondName") val secondName: String,
    @Json(name = "profileImage") val imageUrl: String,
){
    fun getName(): String{
        return "$firstName $secondName"
    }
}