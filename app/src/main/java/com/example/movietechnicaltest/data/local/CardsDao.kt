package com.example.movietechnicaltest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CardsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCards(
        companyListingEntity: List<CardsEntity>
    )

    @Query("DELETE FROM cardsentity")
    suspend fun clearCards()

    @Query(
        """
    SELECT * 
    FROM CardsEntity
    WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
    UPPER(:query) == frameType
        """
    )
    suspend fun searchCards(query: String): List<CardsEntity>

    @Query(
        """
    SELECT * 
    FROM CardsEntity
    WHERE isFavorite == 1
    """
    )
    suspend fun searchFavoriteCards(): List<CardsEntity>

    @Query(
        """
        UPDATE CardsEntity
        SET isFavorite = :isFavorite
        WHERE id = :id
    """
    )
    suspend fun updateIsFavorite(id: Int, isFavorite: Boolean): Int
}