package com.example.projectdemo.domain.usecaserepository

import com.example.projectdemo.data.local.repository.LocalMovieRepository
import com.example.projectdemo.data.remote.repository.RepositoryMovie
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VideoUseCase@Inject constructor(
    private val repositoryMovie: RepositoryMovie,
    private val localMovieRepository: LocalMovieRepository
) {
    fun videoUseCaseFun(ContentType: Int) =
        flow { emit(repositoryMovie.repoVideoFun(ContentType)) }
}