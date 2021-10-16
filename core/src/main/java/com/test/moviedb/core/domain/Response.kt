package com.test.moviedb.core.domain

sealed class Response<out T> {
    object UnInitialized : Response<Nothing>()
    object Loading : Response<Nothing>()
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val error: String) : Response<Nothing>()
}
