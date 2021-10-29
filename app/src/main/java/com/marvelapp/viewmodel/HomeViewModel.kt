package com.marvelapp.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.marvelapp.repository.model.Character
import com.marvelapp.repository.network.repository.CharactersRepository
import com.marvelapp.repository.network.response.ErrorResponse
import com.marvelapp.view.adapters.CharacterDataSource
import com.marvelapp.view.adapters.HomeAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel(){

    private lateinit var characters: Flow<*>
    val adapterHome = HomeAdapter()

    fun init(context: Context){

        characters = Pager(PagingConfig(pageSize = CharacterDataSource.LIMIT)){
            CharacterDataSource(CharactersRepository.getRestInterface(context))
        }.flow.cachedIn(viewModelScope)

        adapterHome.addLoadStateListener {

            when (val loadState = it.source.refresh) {
                is LoadState.NotLoading -> {
                    loading.value = false
                }
                is LoadState.Loading -> {
                    loading.value = true
                }
                is LoadState.Error -> {
                    error.value = ErrorResponse(true, -1, loadState.error.localizedMessage)
                }
            }
        }
    }


    fun getCharacters(){

        launch {

            characters.collectLatest {
                adapterHome.submitData(it as PagingData<Character>)
            }
        }
    }
}