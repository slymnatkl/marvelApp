package com.marvelapp.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.marvelapp.repository.model.Character
import com.marvelapp.repository.network.repository.CharactersRepository
import com.marvelapp.repository.network.response.ErrorResponse
import com.marvelapp.view.adapters.CharactersDataSource
import com.marvelapp.view.adapters.CharacterListAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel(){

    private lateinit var characters: Flow<*>
    val adapterCharacterList = CharacterListAdapter()

    fun init(context: Context){

        characters = Pager(PagingConfig(pageSize = CharactersDataSource.LIMIT)){
            CharactersDataSource(CharactersRepository.getRestInterface(context))
        }.flow.cachedIn(viewModelScope)

        adapterCharacterList.addLoadStateListener {

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

        if(adapterCharacterList.itemCount <= 0)
            getCharacters()
    }

    private fun getCharacters(){

        launch {

            characters.collectLatest {
                adapterCharacterList.submitData(it as PagingData<Character>)
            }
        }
    }
}