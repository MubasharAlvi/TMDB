package com.example.projectdemo.presentation.main.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectdemo.domain.usecaserepository.MovieUseCase
import com.example.projectdemo.domain.usecaserepository.SearchMovieUseCase
import com.example.projectdemo.domain.usecaserepository.VideoUseCase
import com.example.projectdemo.utils.ResourceApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val searchMovieUseCase: SearchMovieUseCase,
    private val videoUseCase: VideoUseCase
) : ViewModel() {

    private val _apiStateFlow: MutableStateFlow<ResourceApiState> =
        MutableStateFlow(ResourceApiState.Empty)
    val apiStateFlow: StateFlow<ResourceApiState> = _apiStateFlow


    fun movieFun(ContentType: String) {
        viewModelScope.launch {
            movieUseCase.latestMovieUseCaseFun(ContentType)
                .onStart {
                    _apiStateFlow.value = ResourceApiState.Loading
                }
                .catch { e ->
                    _apiStateFlow.value = ResourceApiState.Error(e.toString())
                }
                .collect { response ->
                    _apiStateFlow.value = ResourceApiState.Success(response)
                    Log.e("main", "$response")
                }
        }
    }


    fun searchFun(ContentType: String) {
        viewModelScope.launch {

            searchMovieUseCase.searchFun(ContentType)
                .onStart {
                    _apiStateFlow.value = ResourceApiState.Loading
                }
                .catch { e ->
                    _apiStateFlow.value = ResourceApiState.Error(e.toString())
                }
                .collect { response ->
                    _apiStateFlow.value = ResourceApiState.Success(response)
                    Log.e("main", "$response")
                }
        }
    }

    fun videoViewModelFun(ContentType: Int) {
        viewModelScope.launch {
            videoUseCase.videoUseCaseFun(ContentType)
                .onStart {
                    _apiStateFlow.value = ResourceApiState.Loading
                }
                .catch { e ->
                    _apiStateFlow.value = ResourceApiState.Error(e.toString())
                }
                .collect { response ->
                    _apiStateFlow.value = ResourceApiState.VideoSuccess(response)
                }
        }
    }

//    fun localGetAllMovies() {
//        viewModelScope.launch {
//            movieUseCase.localGetAllMovies()
//                .onStart {
//                    _apiStateFlow.value = ResourceApiState.Loading
//                }
//                .catch { e ->
//                    _apiStateFlow.value = ResourceApiState.Error(e)
//                }
//                .collect { response ->
//                    _apiStateFlow.value = ResourceApiState.LocalSuccess(response)
//                    Log.e("viewmodel", "$response")
//                }
//        }
//    }
//
//    fun localInsertMovies(ContentType: List<MovieModelClass>) {
//        viewModelScope.launch {
//            movieUseCase.localInsertMovies(ContentType)
//            Log.e("viewmodel", "insert")
//        }
//
//    }
//
//    fun localDeleteAllMovies() {}
//    fun localSearchMovie() {}


}