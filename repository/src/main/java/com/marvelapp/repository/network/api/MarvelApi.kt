package com.marvelapp.repository.network.api

import com.marvelapp.repository.model.Character
import com.marvelapp.repository.model.Comic
import com.marvelapp.repository.network.response.BaseListResponse
import com.marvelapp.repository.network.response.BaseResponse
import com.marvelapp.repository.utils.Utils
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): BaseResponse<BaseListResponse<Character>>

    @GET("characters/{characterId}/comics")
    fun getComics(
        @Path("characterId") characterId: Int,
        @Query("dateRange") dateRange: String = "2005-01-01," + Utils.dateToString(Date(), "yyyy-MM-dd"),
        @Query("orderBy") orderBy: String = "-focDate",
        @Query("limit") limit: Int = 10
    ): Call<BaseResponse<BaseListResponse<Comic>>>
}