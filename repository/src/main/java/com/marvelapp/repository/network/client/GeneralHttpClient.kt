package com.marvelapp.repository.network.client

import android.content.Context
import okhttp3.OkHttpClient

object GeneralHttpClient: BaseOkHttpClient() {

    fun getOkHttpClient(context: Context): OkHttpClient{

        val builder = OkHttpClient.Builder()
        return buildClient(builder, context)
    }
}