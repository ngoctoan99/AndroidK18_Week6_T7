package com.example.androidweek6.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidweek6.model.Movie
import com.example.androidweek6.service.MovieRestClient
import kotlinx.coroutines.launch

class TopRatedVM : ViewModel() {
    private var _movieData: MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>()
    val movieData: LiveData<List<Movie>>
        get() = _movieData

    private var _errEvent: MutableLiveData<String> = MutableLiveData<String>()
    val errEvent: LiveData<String>
        get() = _errEvent
    fun getTopRatedMovie() {
        viewModelScope.launch {
            val movieResp = MovieRestClient.getInstance().api.listTopRatedMovies(
                language = "vi-VN",
                page = 1,
            )
            _movieData.postValue(movieResp.results!!)
        }
    }
}