package com.seytkalievm.angimehubnative.domain.model

import com.squareup.moshi.Json


data class ShowPreview (
    @Json(name = "urlImage") val imageUrl: String,
    @Json(name = "urlVideo") val videoUrl: String,
    @Json(name = "mediaName") val name: String,
    @Json(name = "idVideo") val id: Int,
    @Json(name = "artistFirstName") val artistFirstName: String,
    @Json(name = "artistSecondName") val artistSecondName: String,
){
    fun artistName(): String{
        return "$artistFirstName $artistSecondName"
    }
}