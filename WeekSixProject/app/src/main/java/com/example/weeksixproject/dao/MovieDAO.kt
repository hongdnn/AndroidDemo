package com.example.weeksixproject.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weeksixproject.entity.Movie

@Dao
interface MovieDAO {

    @Query("SELECT * from movie ORDER BY movieId ASC")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * from movie WHERE category = :categoryName")
    fun getMoviesByCategory(categoryName: String): LiveData<List<Movie>>

    @Query("SELECT * from movie WHERE category = :categoryName AND movieName LIKE '%' || :searchName || '%' ")
    fun getAllResults(searchName: String, categoryName: String): LiveData<List<Movie>>

    @Insert
    suspend fun insert(movie: Movie)

}