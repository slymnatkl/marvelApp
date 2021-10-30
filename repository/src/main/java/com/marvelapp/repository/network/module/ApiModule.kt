package com.marvelapp.repository.network.module

import android.content.Context
import com.marvelapp.repository.BuildConfig
import com.marvelapp.repository.network.api.MarvelApi
import com.marvelapp.repository.network.interceptor.GeneralInterceptor
import com.marvelapp.repository.network.interceptor.NetworkConnectionInterceptor
import com.marvelapp.repository.network.repository.Repository
import com.marvelapp.repository.network.utils.ApiUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            if(BuildConfig.DEBUG)
                level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesGeneralInterceptor() = GeneralInterceptor()

    @Singleton
    @Provides
    fun providesNetworkConnectionInterceptor(@ApplicationContext context : Context) = NetworkConnectionInterceptor(context)

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        generalInterceptor: GeneralInterceptor,
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(generalInterceptor)
            .addInterceptor(networkConnectionInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(ApiUtils.API_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): MarvelApi = retrofit.create(MarvelApi::class.java)

    @Singleton
    @Provides
    fun providesRepository(apiService: MarvelApi) = Repository(apiService)
}