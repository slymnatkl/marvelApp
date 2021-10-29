package com.marvelapp.view.adapters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marvelapp.repository.model.Character
import com.marvelapp.repository.network.api.MarvelApi

class CharactersDataSource(private val api: MarvelApi): PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {

        return try {
            val nextPageNumber = params.key ?: STARTING_INDEX
            val response = api.getCharacters(LIMIT, (nextPageNumber * LIMIT))

            LoadResult.Page(
                data = response.data!!.results!!,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        const val STARTING_INDEX    = 0
        const val LIMIT             = 30
    }
}