package com.marvelapp.view.adapters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.marvelapp.repository.model.Character
import com.marvelapp.repository.network.repository.Repository
import javax.inject.Inject

class CharactersDataSource @Inject constructor(private val repository: Repository): PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {

        return try {
            val pageNumber = params.key ?: STARTING_INDEX
            val response = repository.getCharacters(LIMIT, (pageNumber * LIMIT))

            val nextPageNumber = response.data?.results?.let { pageNumber + 1 } ?: run { null }

            LoadResult.Page(
                data = response.data?.results ?: run { emptyList() },
                prevKey = null,
                nextKey = nextPageNumber
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