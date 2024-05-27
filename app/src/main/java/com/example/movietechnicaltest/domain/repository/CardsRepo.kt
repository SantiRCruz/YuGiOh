package com.example.movietechnicaltest.domain.repository

import com.example.movietechnicaltest.domain.models.Archetype
import com.example.movietechnicaltest.domain.models.Card
import com.example.movietechnicaltest.util.Resource
import kotlinx.coroutines.flow.Flow

interface CardsRepo {
    suspend fun getCards(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<Card>>>

    suspend fun searchFavoriteCards(
    ): Flow<Resource<List<Card>>>

    suspend fun updateIsFavorite(
        id: Int, isFavorite: Boolean
    ): Flow<Resource<Int>>

    suspend fun getArchetypes(): Flow<Resource<List<Archetype>>>

}