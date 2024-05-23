package com.example.movietechnicaltest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DataDto(
    val archetype: String?,
    val atk: Int?,
    val attribute: String?,
    val def: Int?,
    val desc: String?,
    val frameType: String?,
    val id: Int?,
    val level: Int?,
    val name: String?,
    val race: String?,
    val type: String?,
    @field:SerializedName("ygoprodeck_url")
    val url: String?,
    @field:SerializedName("card_images")
    val images: List<CardImageDto>?
)