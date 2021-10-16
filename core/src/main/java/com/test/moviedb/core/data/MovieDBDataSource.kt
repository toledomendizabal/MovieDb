package com.test.moviedb.core.data

import com.test.moviedb.core.domain.MovieDBItem
import com.test.moviedb.core.domain.Response

interface MovieDBDataSource {
    suspend fun getPopularMovies(): Response<List<MovieDBItem>>
    suspend fun getPopularTvShows(): Response<List<MovieDBItem>>
    suspend fun getNowPlayingMovies(): Response<List<MovieDBItem>>
    suspend fun getTopRatedTvShows(): Response<List<MovieDBItem>>
    suspend fun getMovieDetail(id: Int): Response<MovieDBItem>
    suspend fun getTvShowsDetail(id: Int): Response<MovieDBItem>
}