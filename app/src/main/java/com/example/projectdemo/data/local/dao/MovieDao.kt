package com.example.projectdemo.data.local.dao

import androidx.room.*
import com.example.projectdemo.data.remote.model.MovieModelClass

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies : List<MovieModelClass>)

    @Query("SELECT * FROM movie_table ORDER BY ids ASC")
    suspend fun getAllMovie(): List<MovieModelClass>

    @Query("SELECT * FROM movie_table WHERE title LIKE '%' || :title || '%'")
    suspend fun getMovie(title : String): List<MovieModelClass>

    @Query("DELETE FROM movie_table")
    suspend fun deleteMovie()

}