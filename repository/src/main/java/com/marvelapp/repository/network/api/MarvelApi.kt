package com.marvelapp.repository.network.api

import com.marvelapp.repository.network.response.BaseResponse
import com.marvelapp.repository.network.response.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): BaseResponse<CharactersResponse>
}