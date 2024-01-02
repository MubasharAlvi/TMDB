package com.example.projectdemo.utils

import com.example.projectdemo.data.remote.model.MovieModel
import com.example.projectdemo.data.remote.model.MovieModelClass
import com.example.projectdemo.data.remote.model.VideoModel


sealed class ResourceApiState {
    object Loading : ResourceApiState()
    object Empty : ResourceApiState()
    class Success(val response: MovieModel) : ResourceApiState()
    class Succes(val response: MovieModelClass) : ResourceApiState()

//    class LocalSuccess(val response: List<MovieEntity>) : ResourceApiState()
    class VideoSuccess(val response: VideoModel) : ResourceApiState()
    class Error(val error: String) : ResourceApiState()
}
