package com.example.movietechnicaltest.data.remote

import com.example.movietechnicaltest.data.remote.dto.CardsDto
import com.example.movietechnicaltest.data.remote.dto.archetype.ArchetypesDto
import retrofit2.http.*

interface YuGiOhApi {

    @GET(CARD)
    suspend fun getCards(@Query("archetype") archetype: String): CardsDto

    @GET(ARCHETYPES)
    suspend fun getArchetypes(): List<ArchetypesDto>

    companion object {
        internal const val CARD = "cardinfo.php"
        internal const val ARCHETYPES = "archetypes.php/"
    }
}