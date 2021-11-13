package com.marvelapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marvelapp.repository.network.response.ErrorResponse

abstract class BaseViewModel : ViewModel(){

    val error = MutableLiveData<ErrorResponse>()
    val loading = MutableLiveData<Boolean>()
}