package com.marvelapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.marvelapp.repository.model.Character
import com.marvelapp.repository.network.repository.BaseRepository
import com.marvelapp.repository.network.repository.CharactersRepository
import com.marvelapp.repository.network.response.BaseResponse
import com.marvelapp.repository.network.response.CharactersResponse
import com.marvelapp.repository.network.response.ErrorResponse
import com.marvelapp.view.adapters.HomeAdapter
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel(){

    val characters = MutableLiveData<List<Character>>()
    val adapterHome = HomeAdapter()

    fun getCharacters(context: Context){

        launch {

            loading.value = true

            CharactersRepository.getCharacters(context, object : BaseRepository.TaskCompletedListener{
                override fun <T> onTaskCompleted(isError: Boolean, code: Int, message: String?, result: T) {

                    if(!isError){
                        val response = (result as BaseResponse<CharactersResponse>)

                        characters.value = response.data?.results
                        adapterHome.setItems(characters.value!!)
                    }
                    else
                        error.value = ErrorResponse(isError, code, message)

                    loading.value = false
                }
            })
        }
    }
}