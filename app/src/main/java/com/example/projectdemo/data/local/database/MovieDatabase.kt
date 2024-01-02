package com.example.projectdemo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projectdemo.data.local.dao.MovieDao
import com.example.projectdemo.data.remote.model.MovieModelClass

@Database(entities = [MovieModelClass::class], version = 1)
abstract class MovieDatabase :RoomDatabase(){

    abstract fun movieDao(): MovieDao
}