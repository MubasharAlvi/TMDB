package com.example.projectdemo.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.projectdemo.data.local.database.MovieDatabase
import com.example.projectdemo.data.local.implementation.LocalMovieRepoImplementation
import com.example.projectdemo.data.local.repository.LocalMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Provides
    @Singleton
    fun providesMovieDao(movieDatabase: MovieDatabase) = movieDatabase.movieDao()

    @Provides
    @Singleton
    fun providesMovieDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MovieDatabase::class.java, "movie_database")
            .build()

    @Provides
    @Singleton
    fun providesMovieRepository(localMovieRepoImplementation: LocalMovieRepoImplementation): LocalMovieRepository = localMovieRepoImplementation


}