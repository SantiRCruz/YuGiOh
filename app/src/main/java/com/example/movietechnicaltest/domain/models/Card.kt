package com.example.movietechnicaltest.domain.models

data class Card(
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
    var isFavorite: Boolean,
    val id: Int
)
