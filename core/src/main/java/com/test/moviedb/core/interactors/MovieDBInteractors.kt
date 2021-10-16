package com.test.moviedb.core.interactors

import com.test.moviedb.core.usecases.*

data class MovieDBInteractors(
    val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    val getPopularTvShowsUseCase: GetPopularTvShowsUseCase,
    val getTopRatedTvShowsUseCase: GetTopRatedTvShowsUseCase,
    val getMovieDetailUseCase: GetMovieDetailUseCase,
    val getTvShowsDetailUseCase: GetTvShowsDetailUseCase
)