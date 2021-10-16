package com.test.moviedb.datasource

import android.util.Log
import com.test.moviedb.core.data.MovieDBDataSource
import com.test.moviedb.core.domain.MovieDBItem
import com.test.moviedb.core.domain.Response
import com.test.moviedb.network.Api

class MovieDBNetworkDataSource(private val api: Api) : MovieDBDataSource {

    private var currentPlayingMoviesPage = 1
    private var playingMoviesTotalPages = 1

    private var currentPopularMoviesPage = 1
    private var popularMoviesTotalPages = 1

    private var currentPopularTvShowsPage = 1
    private var popularTvShowsTotalPages = 1

    private var currentTopRatedTvShowsPage = 1
    private var topRatedTvShowsTotalPages = 1

    override suspend fun getPopularMovies(): Response<List<MovieDBItem>> {
        return try {
            val response = api.getPopularMovies(currentPopularMoviesPage)
            popularMoviesTotalPages = response.total_pages
            if(popularMoviesTotalPages > currentPopularMoviesPage) currentPopularMoviesPage++
            if(response.results.isEmpty()) Response.Error("")
            Response.Success(response.results)
        }catch (e:Exception){
            Response.Error(e.message?:"")
        }

    }

    override suspend fun getPopularTvShows(): Response<List<MovieDBItem>> {
        return try {
            val response = api.getPopularTvShows(currentPopularTvShowsPage)
            popularTvShowsTotalPages = response.total_pages
            if(popularTvShowsTotalPages > currentPopularTvShowsPage) currentPopularTvShowsPage++
            if(response.results.isEmpty()) Response.Error("")
            Response.Success(response.results)
        }catch (e:Exception){
            Response.Error(e.message?:"")
        }
    }

    override suspend fun getNowPlayingMovies(): Response<List<MovieDBItem>> {
        return try {
            val response = api.getNowPlayingMovies(currentPlayingMoviesPage)
            playingMoviesTotalPages = response.total_pages
            if(playingMoviesTotalPages > currentPlayingMoviesPage) currentPlayingMoviesPage++
            if(response.results.isEmpty()) Response.Error("")
            Response.Success(response.results)
        }catch (e:Exception){
            Response.Error(e.message?:"")
        }
    }

    override suspend fun getTopRatedTvShows(): Response<List<MovieDBItem>> {
        return try {
            val response = api.getTopRatedTvShows(currentTopRatedTvShowsPage)
            topRatedTvShowsTotalPages = response.total_pages
            if(topRatedTvShowsTotalPages > currentTopRatedTvShowsPage) currentTopRatedTvShowsPage++
            if(response.results.isEmpty()) Response.Error("")
            Response.Success(response.results)
        }catch (e:Exception){
            Response.Error(e.message?:"")
        }
    }
    override suspend fun getMovieDetail(id: Int): Response<MovieDBItem> {
        return try {
            val response = api.getMovieDetail(id,"movie" )
            Log.d("id",response.toString())
            if (response != null) Response.Success(response)
            else Response.Error("")
        } catch (e: Exception) {
            Response.Error(e.message ?: "")
        }
    }

    override suspend fun getTvShowsDetail(id: Int): Response<MovieDBItem> {
        return try {
            val response = api.getMovieDetail(id, "tv")
            Log.d("id",response.toString())
            if (response != null) Response.Success(response)
            else Response.Error("")
        } catch (e: Exception) {
            Response.Error(e.message ?: "")
        }
    }

}