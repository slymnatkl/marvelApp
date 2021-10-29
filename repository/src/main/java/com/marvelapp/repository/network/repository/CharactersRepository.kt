package com.marvelapp.repository.network.repository

import android.content.Context
import com.marvelapp.repository.network.api.MarvelApi

object CharactersRepository : BaseRepository() {

    private fun getRestInterface(context: Context): MarvelApi {

        return getRetrofit(context).create(MarvelApi::class.java)
    }

    fun getCharacters(context: Context, taskCompletedListener: TaskCompletedListener?) {

        callRequest(getRestInterface(context).getCharacters(), taskCompletedListener)
    }
}