package com.test.moviedb.core.interactors

import com.test.moviedb.core.usecases.GetMovieDetailUseCase
import com.test.moviedb.core.usecases.GetTvShowsDetailUseCase

data class MovieDBDetailInteractors(
    val getMovieDetailUseCase: GetMovieDetailUseCase,
    val getTvShowsDetailUseCase: GetTvShowsDetailUseCase
)
