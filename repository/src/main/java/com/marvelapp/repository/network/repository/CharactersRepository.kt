package com.marvelapp.repository.network.repository

import android.content.Context
import com.marvelapp.repository.network.api.MarvelApi

object CharactersRepository : BaseRepository() {

    fun getRestInterface(context: Context): MarvelApi {

        return getRetrofit(context).create(MarvelApi::class.java)
    }

}