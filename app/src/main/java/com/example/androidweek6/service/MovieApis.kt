package com.example.androidweek6.service
import com.example.androidweek6.model.MovieResp
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApis {
    @GET("movie/now_playing")
    suspend fun listNowPlayMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ):MovieResp

    @GET("movie/top_rated")
    suspend fun listTopRatedMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ):MovieResp
}