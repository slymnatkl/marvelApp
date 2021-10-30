package com.marvelapp.repository.network.repository

import com.marvelapp.repository.network.api.MarvelApi

class Repository(private val apiService: MarvelApi){

    suspend fun getCharacters(limit: Int, offset: Int) = apiService.getCharacters(limit, offset)

    suspend fun getComics(characterId: Int) = apiService.getComics(characterId)
}