package com.example.weeksixproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.weeksixproject.db.MovieRoomDatabase
import com.example.weeksixproject.entity.Category
import com.example.weeksixproject.entity.Movie
import com.example.weeksixproject.repo.MovieRepository
import kotlinx.coroutines.*

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MovieRepository
    private val allMovies: LiveData<List<Movie>>
        get() {
            return repository.allMovies
        }
    val allCategories: LiveData<List<Category>>

    init {
        val movieDAO = MovieRoomDatabase.getDatabase(application, viewModelScope).movieDAO()
        val categoryDAO = MovieRoomDatabase.getDatabase(application, viewModelScope).categoryDAO()
        repository = MovieRepository(movieDAO,categoryDAO)
        allCategories = repository.allCategories
    }

    fun searchMovie(searchName: String,categoryName: String){
        repository.searchResult(searchName,categoryName)
    }

    fun insertMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertMovie(movie)
    }

    fun insertCategory(category: Category): Boolean{
        var result=false
        viewModelScope.launch(Dispatchers.IO) {
            result = repository.insertCategory(category)
        }
        return result
    }
//    val insertCategory:(category: Category) -> Boolean ={
//        var result: Boolean =false
//         viewModelScope.launch(Dispatchers.IO) {
//             result = repository.insertCategory(it)
//        }
//        result
//    }

}