package com.test.moviedb.core.domain

data class MovieDBResponse(
    val dates: Date?,
    val page: Int,
    val results: List<MovieDBItem>,
    val total_pages: Int,
    val total_results: Int
)

data class Date(val maximum: String, val minimum: String)

data class MovieDBItem(
    val id: Int,
    val backdrop_path: String,
    val poster_path: String,
    val original_title: String?,
    val original_name: String?,
    val overview: String
)

