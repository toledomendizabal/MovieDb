package com.test.moviedb.core.usecases

import com.test.moviedb.core.data.MovieDBRepository

class GetTvShowsDetailUseCase(private val repository: MovieDBRepository) {
    suspend fun invoke(id: Int) = repository.getTvShowsDetail(id)
}