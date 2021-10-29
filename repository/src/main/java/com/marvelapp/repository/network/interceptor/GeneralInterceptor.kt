package com.marvelapp.repository.network.interceptor

import com.marvelapp.repository.network.utils.ApiUtils
import com.marvelapp.repository.utils.Utils
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import java.util.*

class GeneralInterceptor: Interceptor{

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {

        val original = chain.request()
        val originalHttpUrl = original.url

        val urlBuilder: HttpUrl.Builder = originalHttpUrl.newBuilder()

        //Adding default parameters
        val timeStamp = Date().time.toString()
        urlBuilder.addQueryParameter(ApiUtils.PARAM_TIMESTAMP, timeStamp)
        urlBuilder.addQueryParameter(ApiUtils.PARAM_APIKEY, ApiUtils.PUBLIC_KEY)
        urlBuilder.addQueryParameter(ApiUtils.PARAM_HASH, Utils.md5(timeStamp + ApiUtils.PRIVATE_KEY + ApiUtils.PUBLIC_KEY))

        val url: HttpUrl = urlBuilder.build()

        // Request customization: add request headers
        val requestBuilder: Request.Builder = original.newBuilder().url(url)

        //Adding header
        //requestBuilder.addHeader("", "");

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}