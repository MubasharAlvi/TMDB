package com.example.projectdemo.data.remote.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie_table")
data class MovieModelClass(
    val poster_path: String? = null,
    val title: String,
    val id: Int,
    val release_date: String,
    val overview: String,
    @PrimaryKey val ids : Int?=null
) : Parcelable

