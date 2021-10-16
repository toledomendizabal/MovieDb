package com.test.moviedb.di

import com.test.moviedb.core.data.MovieDBDataSource
import com.test.moviedb.core.data.MovieDBRepository
import com.test.moviedb.core.interactors.MovieDBDetailInteractors
import com.test.moviedb.core.interactors.MovieDBInteractors
import com.test.moviedb.core.usecases.*
import com.test.moviedb.datasource.MovieDBNetworkDataSource
import com.test.moviedb.network.NetworkService
import com.test.moviedb.presentatiion.DetailViewModel
import com.test.moviedb.presentatiion.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    single { NetworkService.service }
}

val movieModule = module {
    factory<MovieDBDataSource> { MovieDBNetworkDataSource(get()) }
    single { MovieDBRepository(get()) }
    single { GetPopularMoviesUseCase(get()) }
    single { GetNowPlayingMoviesUseCase(get()) }
    single { GetPopularTvShowsUseCase(get()) }
    single { GetTopRatedTvShowsUseCase(get()) }
    single { GetMovieDetailUseCase(get()) }
    single { GetTvShowsDetailUseCase(get()) }
    single { MovieDBInteractors(get(),get(),get(),get(),get(),get()) }
    viewModel{DetailViewModel(get())}
}


val mainModule = module {
    viewModel { MainViewModel(get()) }
}