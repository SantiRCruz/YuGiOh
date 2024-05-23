package com.example.movietechnicaltest.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movietechnicaltest.data.remote.dto.CardImageDto
import com.google.gson.annotations.SerializedName

@Entity
data class CardsEntity(
    val archetype: String,
    val atk: Int,
    val attribute: String,
    val def: Int,
    val desc: String,
    val frameType: String,
    val level: Int,
    val name: String,
    val race: String,
    val type: String,
    val imageUrl: String,
    val isFavorite: Boolean,
    @PrimaryKey val id: Int
)
