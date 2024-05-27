package com.example.movietechnicaltest.data.repository

import android.app.Application
import com.example.movietechnicaltest.data.local.YuGiOhDatabase
import com.example.movietechnicaltest.data.mapper.toArchetype
import com.example.movietechnicaltest.data.mapper.toCard
import com.example.movietechnicaltest.data.mapper.toCardsEntity
import com.example.movietechnicaltest.data.remote.YuGiOhApi
import com.example.movietechnicaltest.domain.models.Archetype
import com.example.movietechnicaltest.domain.models.Card
import com.example.movietechnicaltest.domain.repository.CardsRepo
import com.example.movietechnicaltest.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CardsRepoImpl @Inject constructor(
    private val api: YuGiOhApi,
    private val db: YuGiOhDatabase,
    private val appContext: Application
) : CardsRepo {

    private val dao = db.dao
    override suspend fun getCards(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<Card>>> {
        return flow {
            emit(Resource.Loading(true))
            val cardsListing = dao.searchCards(query)
            emit(
                Resource.Success(
                    data = cardsListing.map { it.toCard() }
                )
            )

            val isDbEmpty = cardsListing.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListing = try {
                api.getCards("Amazoness")
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListing?.let { cards ->
                dao.clearCards()
                dao.insertCards(
                    cards.data.map { it.toCardsEntity() }
                )
                emit(
                    Resource.Success(
                        data = dao
                            .searchCards(query)
                            .map { it.toCard() }
                    )
                )
                emit(Resource.Loading(false))
            }
        }
    }

    private fun buildCardsFilter(archetype: String): Map<String, String> {
        return mapOf(
            "archetype" to archetype,
        )
    }

    override suspend fun searchFavoriteCards(): Flow<Resource<List<Card>>> {
        return flow {
            emit(Resource.Loading(true))
            val favoriteCards = dao.searchFavoriteCards()
            emit(
                Resource.Success(
                    data = favoriteCards.map { it.toCard() }
                )
            )
            emit(Resource.Loading(false))
        }
    }

    override suspend fun updateIsFavorite(id: Int, isFavorite: Boolean): Flow<Resource<Int>> {
        return flow {
            emit(Resource.Loading(true))
            val updateCard = dao.updateIsFavorite(id, isFavorite)
            if (updateCard > 0) {
                emit(
                    Resource.Success(
                        data = updateCard
                    )
                )
            } else {
                emit(
                    Resource.Error(
                        message = "No se logro guardar en tus favoritos"
                    )
                )
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getArchetypes(): Flow<Resource<List<Archetype>>> {
        return flow {
            emit(Resource.Loading(true))
            val archetypes = api.getArchetypes()
            try {
                emit(Resource.Success(archetypes.map {
                    it.toArchetype()
                }))
            } catch (e: Exception) {
                emit(Resource.Error("Hubo un fallo"))
            }
            emit(Resource.Loading(false))
        }
    }
}