//package com.example.projectdemo.di
//
//import android.content.Context
//import androidx.room.Room
//import com.example.projectdemo.data.local.implementationclass.LocalRepositoryMovieImplementation
//import com.example.projectdemo.data.local.module.MovieDataBase
//import com.example.projectdemo.data.local.network.MovieDao
//import com.example.projectdemo.data.local.repository.DatabaseRepositoryMovie
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object HilModule {
//
//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context): MovieDataBase {
//        return Room.databaseBuilder(
//            context, MovieDataBase::class.java, "movie_database"
//        ).allowMainThreadQueries().build()
//    }
//
//    @Provides
//    fun provideDoa(movieDataBase: MovieDataBase): MovieDao {
//        return movieDataBase.movieDao()
//    }
//
//    @Provides
//    fun fetchData(movieDataBase: MovieDataBase): DatabaseRepositoryMovie {
//        return LocalRepositoryMovieImplementation(movieDataBase)
//    }
//}