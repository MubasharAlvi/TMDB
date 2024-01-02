package com.example.projectdemo.data.remote.network

import com.example.projectdemo.data.remote.model.MovieModel
import com.example.projectdemo.data.remote.model.VideoModel
import com.example.projectdemo.utils.Constant
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("movie/{contentType}")
    suspend fun popularFun(
        @Path("contentType") content : String,
        @Query("api_key") apiKey : String = Constant.API_KEY
    ): Response<MovieModel>
    @GET("movie/{contentType}")
    suspend fun latestMovieFun(
        @Path("contentType") content : String,
        @Query("api_key") apiKey : String = Constant.API_KEY
    ): MovieModel
    @GET("movie/{contentType}/videos")
    suspend fun videoPlay(
        @Path("contentType") content : Int,
        @Query("api_key") apiKey : String = Constant.API_KEY
    ): VideoModel

    @GET("search/movie")
    suspend fun searchFun(
        @Query("query") query : String,
        @Query("api_key") apiKey : String = Constant.API_KEY

    ): MovieModel

}