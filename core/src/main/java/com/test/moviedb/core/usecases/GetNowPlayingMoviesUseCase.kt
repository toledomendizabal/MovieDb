package com.test.moviedb.core.usecases

import com.test.moviedb.core.data.MovieDBRepository

class GetNowPlayingMoviesUseCase(private val repository: MovieDBRepository) {
    suspend fun invoke() = repository.getNowPlayingMovies()
}