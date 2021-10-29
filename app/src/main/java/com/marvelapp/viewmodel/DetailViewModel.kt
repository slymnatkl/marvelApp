package com.marvelapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.marvelapp.repository.model.Comic
import com.marvelapp.repository.network.repository.BaseRepository
import com.marvelapp.repository.network.repository.CharactersRepository
import com.marvelapp.repository.network.response.BaseListResponse
import com.marvelapp.repository.network.response.BaseResponse
import com.marvelapp.repository.network.response.ErrorResponse
import com.marvelapp.view.adapters.ComicListAdapter
import kotlinx.coroutines.launch

class DetailViewModel : BaseViewModel(){

    val comics = MutableLiveData<List<Comic>>()
    val adapterComicList = ComicListAdapter()

    fun init(context: Context, characterId: Int){

        if(adapterComicList.itemCount <= 0)
            getComics(context, characterId)
    }

    fun getComics(context: Context, characterId: Int){

        launch {

            loading.value = true

            CharactersRepository.getComics(context, characterId, object : BaseRepository.TaskCompletedListener{
                override fun <T> onTaskCompleted(isError: Boolean, code: Int, message: String?, result: T) {

                    if(!isError){

                        comics.value = (result as BaseResponse<BaseListResponse<Comic>>).data!!.results
                        this@DetailViewModel.adapterComicList.setItems(comics.value!!)
                    }
                    else
                        error.value = ErrorResponse(isError, code, message)

                    loading.value = false
                }
            })
        }
    }
}