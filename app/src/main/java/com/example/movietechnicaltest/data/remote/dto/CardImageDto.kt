package com.example.movietechnicaltest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CardImageDto(
    val id: Int,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("image_url_small")
    val imageUrlSmall: String,

    @field:SerializedName("image_url_cropped")
    val imageUrlCropped: String
)