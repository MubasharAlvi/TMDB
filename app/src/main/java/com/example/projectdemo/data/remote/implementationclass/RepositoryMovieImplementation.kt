package com.example.projectdemo.data.remote.implementationclass

import com.example.projectdemo.data.remote.network.ApiService
import com.example.projectdemo.data.remote.repository.RepositoryMovie
import javax.inject.Inject

class RepositoryMovieImplementation @Inject constructor(
    private val apiService: ApiService
) : RepositoryMovie {
    override suspend fun repositoryFun(ContentType: String) = apiService.popularFun(ContentType)
    override suspend fun repoLatestMovieFun(ContentType: String) =
        apiService.latestMovieFun(ContentType)
    override suspend fun repoVideoFun(ContentType: Int) = apiService.videoPlay(ContentType)
    override suspend fun repoSearch(query: String)=apiService.searchFun(query)
}