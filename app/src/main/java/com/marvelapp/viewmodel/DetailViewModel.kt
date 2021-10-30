package com.marvelapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.marvelapp.repository.model.Comic
import com.marvelapp.repository.network.repository.Repository
import com.marvelapp.repository.network.response.ErrorResponse
import com.marvelapp.view.adapters.ComicListAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository) : BaseViewModel(){

    val comics = MutableLiveData<List<Comic>>()
    val adapterComicList = ComicListAdapter()

    fun init(characterId: Int){

        if(adapterComicList.itemCount <= 0)
            getComics(characterId)
    }

    private fun getComics(characterId: Int){

        launch {

            loading.value = true

            try {
                val comicResult = repository.getComics(characterId)
                comics.value = comicResult.data!!.results
                this@DetailViewModel.adapterComicList.setItems(comics.value!!)
                loading.value = false
            }
            catch (exception: Exception){
                loading.value = false
                error.value = ErrorResponse(exception.message)
            }
        }
    }
}