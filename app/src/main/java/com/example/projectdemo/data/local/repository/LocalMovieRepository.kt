package com.example.projectdemo.data.local.repository

import com.example.projectdemo.data.remote.model.MovieModelClass

interface LocalMovieRepository {


    suspend fun insertMovie(movie: List<MovieModelClass>)
    suspend fun getAllMovie(): List<MovieModelClass>
    suspend fun getMovie(titleMovie: String): List<MovieModelClass>
    suspend fun deleteMovie()


}