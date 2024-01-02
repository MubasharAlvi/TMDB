package com.example.projectdemo.data.remote.model

data class MovieModel(
    val page: Int,
    val results: List<MovieModelClass>,
    val total_pages: Int
)
