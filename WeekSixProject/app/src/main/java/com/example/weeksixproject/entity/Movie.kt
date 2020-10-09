package com.example.weeksixproject.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")

data class Movie(
    @PrimaryKey(autoGenerate = true) val movieId: Int = 0,
    @ColumnInfo(name = "movieName") val movieName: String,
    @ColumnInfo(name = "category") val category: String

)