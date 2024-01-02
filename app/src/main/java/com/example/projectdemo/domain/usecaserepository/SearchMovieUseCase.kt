package com.example.projectdemo.domain.usecaserepository

import com.example.projectdemo.data.local.repository.LocalMovieRepository
import com.example.projectdemo.data.remote.repository.RepositoryMovie
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchMovieUseCase@Inject constructor(
    private val repositoryMovie: RepositoryMovie,
    private val localMovieRepository: LocalMovieRepository
)  {
    fun searchFun(ContentType: String) = flow { emit(repositoryMovie.repoSearch(ContentType)) }

}