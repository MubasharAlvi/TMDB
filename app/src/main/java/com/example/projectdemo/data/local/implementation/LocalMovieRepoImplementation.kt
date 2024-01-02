package com.example.projectdemo.data.local.implementation

import com.example.projectdemo.data.local.dao.MovieDao
import com.example.projectdemo.data.local.repository.LocalMovieRepository
import com.example.projectdemo.data.remote.model.MovieModelClass
import javax.inject.Inject

class LocalMovieRepoImplementation @Inject constructor(private val movieDao: MovieDao) :
    LocalMovieRepository {
    override suspend fun insertMovie(movie: List<MovieModelClass>) = movieDao.insertMovie(movie)

    override suspend fun getAllMovie(): List<MovieModelClass> = movieDao.getAllMovie()

    override suspend fun getMovie(titleMovie: String): List<MovieModelClass> =movieDao.getMovie(titleMovie)

    override suspend fun deleteMovie() =movieDao.deleteMovie()

}