package com.marvelapp.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.marvelapp.repository.model.Character
import com.marvelapp.repository.network.repository.Repository
import com.marvelapp.repository.network.response.ErrorResponse
import com.marvelapp.view.adapters.CharacterListAdapter
import com.marvelapp.view.adapters.CharactersDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository): BaseViewModel(){

    private var characters: Flow<PagingData<Character>> = Pager(PagingConfig(pageSize = CharactersDataSource.LIMIT)){
        CharactersDataSource(repository)
    }.flow.cachedIn(viewModelScope)

    val adapterCharacterList = CharacterListAdapter()

    init {
        viewModelScope.launch {

            adapterCharacterList.loadStateFlow.collectLatest { loadStates ->

                loading.value = (loadStates.refresh is LoadState.Loading)

                if(loadStates.refresh is LoadState.Error)
                    error.value = ErrorResponse((loadStates.refresh as LoadState.Error).error.localizedMessage)
                else
                    error.value = null
            }
        }

        getCharacters()
    }

    private fun getCharacters(){

        viewModelScope.launch {

            characters.collectLatest {
                adapterCharacterList.submitData(it)
            }
        }
    }
}