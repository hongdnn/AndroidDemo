package com.example.weeksixproject.repo

import androidx.lifecycle.LiveData
import com.example.weeksixproject.dao.CategoryDAO
import com.example.weeksixproject.dao.MovieDAO
import com.example.weeksixproject.entity.Category
import com.example.weeksixproject.entity.Movie

class MovieRepository(private val movieDAO: MovieDAO, private val categoryDAO: CategoryDAO) {

    val allCategories: LiveData<List<Category>> = categoryDAO.getAllCategories()

    fun allMovies(): LiveData<List<Movie>> {
        return movieDAO.getAllMovies()
    }

    fun searchResult(searchName: String, searchCategory: String): LiveData<List<Movie>> {
        return movieDAO.getAllResults(searchName, searchCategory)
    }

    fun searchMoviesByCategory(searchCategory: String): LiveData<List<Movie>> {
        return movieDAO.getMoviesByCategory(searchCategory)
    }

    suspend fun insertMovie(movie: Movie) {
        movieDAO.insert(movie)
    }

    suspend fun insertCategory(category: Category): Boolean {
        if (categoryDAO.searchCategory(category.categoryName) == null ) {
            categoryDAO.insert(category)
            return true
        }
        return false
    }
}