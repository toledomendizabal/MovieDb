package com.test.moviedb.presentatiion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.moviedb.core.domain.MovieDBItem
import com.test.moviedb.core.domain.Response
import com.test.moviedb.core.interactors.MovieDBInteractors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val movieDBInteractors: MovieDBInteractors) : ViewModel() {


    private val _popularMovies =
        MutableLiveData<Response<List<MovieDBItem>>>(Response.UnInitialized)
    val popularMovies: LiveData<Response<List<MovieDBItem>>> get() = _popularMovies

    private val _playingNowMovies =
        MutableLiveData<Response<List<MovieDBItem>>>(Response.UnInitialized)
    val playingNowMovies: LiveData<Response<List<MovieDBItem>>> get() = _playingNowMovies

    private val _popularSeries =
        MutableLiveData<Response<List<MovieDBItem>>>(Response.UnInitialized)
    val popularSeries: LiveData<Response<List<MovieDBItem>>> get() = _popularSeries

    private val _topRatedMovies =
        MutableLiveData<Response<List<MovieDBItem>>>(Response.UnInitialized)
    val topRatedMovies: LiveData<Response<List<MovieDBItem>>> get() = _topRatedMovies

    fun loadData() {
        _popularMovies.value = Response.Loading
        _playingNowMovies.value = Response.Loading
        _popularSeries.value = Response.Loading
        _topRatedMovies.value = Response.Loading
        loadPopularMovies()
        loadPlayingNowMovies()
        loadPopularSeries()
        loadTopRatedSeries()

    }

     fun loadPopularMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _popularMovies.postValue(movieDBInteractors.getPopularMoviesUseCase.invoke())
            }
        }
    }

    fun loadPlayingNowMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _playingNowMovies.postValue(movieDBInteractors.getNowPlayingMoviesUseCase.invoke())
            }
        }
    }

     fun loadPopularSeries() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _popularSeries.postValue(movieDBInteractors.getPopularTvShowsUseCase.invoke())
            }
        }
    }

     fun loadTopRatedSeries() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _topRatedMovies.postValue(movieDBInteractors.getTopRatedTvShowsUseCase.invoke())
            }
        }
    }



}