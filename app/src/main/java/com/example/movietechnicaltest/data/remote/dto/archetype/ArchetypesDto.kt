package com.example.movietechnicaltest.data.remote.dto.archetype

import com.google.gson.annotations.SerializedName

data class ArchetypesDto(
    @SerializedName("archetype_name")
    val archetypeName: String?
)
