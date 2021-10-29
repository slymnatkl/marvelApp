package com.marvelapp.repository.network.api

import com.marvelapp.repository.network.response.BaseResponse
import com.marvelapp.repository.network.response.CharactersResponse
import retrofit2.Call
import retrofit2.http.GET

interface MarvelApi {

    @GET("characters")
    fun getCharacters(): Call<BaseResponse<CharactersResponse>>
}