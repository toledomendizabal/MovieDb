package com.test.moviedb.presentatiion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.moviedb.core.domain.MovieDBItem
import com.test.moviedb.core.domain.MovieDBResponse
import com.test.moviedb.core.domain.MovieDbItemDetail
import com.test.moviedb.core.domain.Response
import com.test.moviedb.core.interactors.MovieDBDetailInteractors
import com.test.moviedb.core.interactors.MovieDBInteractors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val movieDBInteractors: MovieDBInteractors):ViewModel() {

    private val _detail = MutableLiveData<Response<MovieDBItem>>(Response.UnInitialized)
    val detail : LiveData<Response<MovieDBItem>> get() = _detail

    fun loadDetail(movieId:Int, isMovie:Boolean){
        if (isMovie)
            viewModelScope.launch(Dispatchers.IO)  {
                _detail.postValue(movieDBInteractors.getMovieDetailUseCase.invoke(movieId))
            }
        else
            viewModelScope.launch(Dispatchers.IO)  {
                _detail.postValue(movieDBInteractors.getTvShowsDetailUseCase.invoke(movieId))
            }
    }
}