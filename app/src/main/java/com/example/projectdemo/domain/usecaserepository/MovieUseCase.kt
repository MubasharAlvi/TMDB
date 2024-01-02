package com.example.projectdemo.domain.usecaserepository

import com.example.projectdemo.data.remote.repository.RepositoryMovie
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MovieUseCase @Inject constructor(
    private val repositoryMovie: RepositoryMovie
) {


    /*unUseLessFun*/
    suspend fun useCaseFun(ContentType: String) = repositoryMovie.repositoryFun(ContentType)

    fun latestMovieUseCaseFun(ContentType: String) =
        flow { emit(repositoryMovie.repoLatestMovieFun(ContentType)) }





}