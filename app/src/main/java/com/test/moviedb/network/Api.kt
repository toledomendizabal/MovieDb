package com.test.moviedb.network

import com.test.moviedb.common.Constants
import com.test.moviedb.core.domain.MovieDBDetailResponse
import com.test.moviedb.core.domain.MovieDBItem
import com.test.moviedb.core.domain.MovieDBResponse
import com.test.moviedb.core.domain.MovieDbItemDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("movie/popular?language=en-US&api_key=${Constants.API_KEY}")
    suspend fun getPopularMovies(@Query("page") page: Int = 1): MovieDBResponse

    @GET("tv/popular?language=en-US&api_key=${Constants.API_KEY}")
    suspend fun getPopularTvShows(@Query("page") page: Int = 1): MovieDBResponse

    @GET("movie/now_playing?language=en-US&api_key=${Constants.API_KEY}")
    suspend fun getNowPlayingMovies(@Query("page") page: Int = 1): MovieDBResponse

    @GET("tv/top_rated?language=en-US&api_key=${Constants.API_KEY}")
    suspend fun getTopRatedTvShows(@Query("page") page: Int = 1): MovieDBResponse

    @GET("{type}/{id}?api_key=${Constants.API_KEY}")
    suspend fun getMovieDetail(
        @Path("id") id: Int,
        @Path("type") type: String
    ): MovieDBItem
}