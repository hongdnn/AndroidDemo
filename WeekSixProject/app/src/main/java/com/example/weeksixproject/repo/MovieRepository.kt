package com.example.weeksixproject.repo

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import com.example.weeksixproject.dao.CategoryDAO
import com.example.weeksixproject.dao.MovieDAO
import com.example.weeksixproject.entity.Category
import com.example.weeksixproject.entity.Movie

class MovieRepository(private val movieDAO: MovieDAO, private val categoryDAO: CategoryDAO) {

    val allMovies: LiveData<List<Movie>> = movieDAO.getAllMovies()
    val allCategories: LiveData<List<Category>> = categoryDAO.getAllCategories()

    fun searchResult(searchName: String, searchCategory: String) {
        movieDAO.getAllResults(searchName, searchCategory)
    }

    suspend fun insertMovie(movie: Movie) {
        movieDAO.insert(movie)
    }

    suspend fun insertCategory(category: Category): Boolean {
        return try {
            categoryDAO.insert(category)
            true
        }catch (e: SQLiteConstraintException){
            false
        }
    }
}