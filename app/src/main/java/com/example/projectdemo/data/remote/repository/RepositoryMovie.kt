package com.example.projectdemo.data.remote.repository

import com.example.projectdemo.data.remote.model.MovieModel
import com.example.projectdemo.data.remote.model.MovieModelClass
import com.example.projectdemo.data.remote.model.VideoModel
import retrofit2.Response

interface RepositoryMovie {
    suspend fun repositoryFun(ContentType: String) : Response<MovieModel>
    suspend fun repoLatestMovieFun(ContentType: String) : MovieModel
    suspend fun repoVideoFun(ContentType: Int): VideoModel
    suspend fun repoSearch(query : String): MovieModel

}