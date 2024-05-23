package com.example.movietechnicaltest.data.remote

import com.example.movietechnicaltest.data.remote.dto.CardsDto
import retrofit2.http.*

interface YuGiOhApi {

    @GET(CARD)
    suspend fun getCards(): CardsDto

    companion object {
        internal const val CARD = "cardinfo.php/"
    }
}