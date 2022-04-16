package com.example.androidweek6.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRestClient {
    private var _api : MovieApis
    val api : MovieApis
        get() = _api
    init {
        _api = createMovieDBservice()
    }
    companion object{
        private var mInstance: MovieRestClient? = null

        fun getInstance() = mInstance ?: synchronized(this){
            mInstance ?: MovieRestClient().also { mInstance = it }
        }
    }
    private fun createMovieDBservice() : MovieApis{
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(AuthenticationInterceptor())
            .build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
        return retrofit.create(MovieApis::class.java)
    }
}