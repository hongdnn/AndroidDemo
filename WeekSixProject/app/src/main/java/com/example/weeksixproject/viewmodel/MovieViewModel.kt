package com.example.weeksixproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weeksixproject.db.MovieRoomDatabase
import com.example.weeksixproject.entity.Category
import com.example.weeksixproject.entity.Movie
import com.example.weeksixproject.repo.MovieRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MovieRepository
    val allCategories: LiveData<List<Category>>

    init {
        val movieDAO = MovieRoomDatabase.getDatabase(application).movieDAO()
        val categoryDAO = MovieRoomDatabase.getDatabase(application).categoryDAO()
        repository = MovieRepository(movieDAO, categoryDAO)
        allCategories= repository.allCategories
    }

    fun allMovies(): LiveData<List<Movie>> {
        return repository.allMovies()
    }

    fun searchResult(searchName: String, categoryName: String): LiveData<List<Movie>> {
        return repository.searchResult(searchName, categoryName)
    }

    fun searchMoviesByCategory(categoryName: String): LiveData<List<Movie>> {
        return repository.searchMoviesByCategory(categoryName)
    }

    fun insertMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertMovie(movie)
    }

    suspend fun insertCategory(category: Category): Boolean {
        var result: Boolean = false
//        viewModelScope.launch(Dispatchers.IO) {
//            withContext(Dispatchers.Main) {
                result = repository.insertCategory(category)
//            }
//        }
        return result
    }

}