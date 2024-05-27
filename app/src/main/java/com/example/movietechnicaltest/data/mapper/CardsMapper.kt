package com.example.movietechnicaltest.data.mapper

import com.example.movietechnicaltest.data.local.CardsEntity
import com.example.movietechnicaltest.data.remote.dto.DataDto
import com.example.movietechnicaltest.data.remote.dto.archetype.ArchetypesDto
import com.example.movietechnicaltest.domain.models.Archetype
import com.example.movietechnicaltest.domain.models.Card


fun CardsEntity.toCard(): Card {
    return Card(
        archetype = archetype,
        atk = atk,
        attribute = attribute,
        def = def,
        desc = desc,
        frameType = frameType,
        level = level,
        name = name,
        race = race,
        type = type,
        imageUrl = imageUrl,
        isFavorite = isFavorite,
        id = id
    )
}

fun DataDto.toCardsEntity(): CardsEntity {
    return CardsEntity(
        archetype = archetype ?: "",
        atk = atk ?: 0,
        attribute = attribute ?: "",
        def = def ?: 0,
        desc = desc ?: "",
        frameType = frameType ?: "",
        level = level ?: 0,
        name = name ?: "",
        race = race ?: "",
        type = type ?: "",
        imageUrl = images?.get(0)?.imageUrl ?: "",
        isFavorite = false,
        id = id ?: 0
    )
}

fun ArchetypesDto.toArchetype(): Archetype {
    return Archetype(
        archetypeName = archetypeName ?: "",
    )
}