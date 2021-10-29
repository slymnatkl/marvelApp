package com.marvelapp.repository.network.repository

import android.content.Context
import com.marvelapp.repository.network.client.GeneralHttpClient
import com.marvelapp.repository.network.utils.ApiUtils
import com.marvelapp.repository.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.json.JSONObject
import java.lang.Exception


open class BaseRepository{

    fun getRetrofit(context: Context): Retrofit{

        return Retrofit.Builder()
            .baseUrl(ApiUtils.API_URL)
            .client(GeneralHttpClient.getOkHttpClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> callRequest(call: Call<T>, taskCompletedListener: TaskCompletedListener?) {

        call.enqueue(object : Callback<T> {

            override fun onResponse(call: Call<T>, response: Response<T>) {

                if(response.isSuccessful){
                    taskCompletedListener?.onTaskCompleted(false, response.code(), response.message(), response.body())
                }
                else{
                    try {
                        val jObjError = JSONObject(response.errorBody()!!.string())

                        val message =
                            if (jObjError.has(ApiUtils.RESPONSE_KEY_MESSAGE))
                                jObjError.getString(ApiUtils.RESPONSE_KEY_MESSAGE)
                            else
                                response.message()

                        taskCompletedListener?.onTaskCompleted(true, response.code(), message, jObjError)
                    }
                    catch (e: Exception) {
                        taskCompletedListener?.onTaskCompleted(true, response.code(), e.toString(), null)
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                t.printStackTrace()
                taskCompletedListener?.onTaskCompleted(true, Constants.CODE_FAILURE, t.message, null)
            }
        })
    }

    //<editor-fold desc="TaskCompletedListener Interface">

    interface TaskCompletedListener{
        fun <T> onTaskCompleted(isError: Boolean, code: Int, message: String?, result: T)
    }

    //</editor-fold>
}