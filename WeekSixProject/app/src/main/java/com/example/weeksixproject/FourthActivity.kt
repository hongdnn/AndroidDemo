package com.example.weeksixproject

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weeksixproject.adapter.MovieListAdapter
import com.example.weeksixproject.entity.Movie
import com.example.weeksixproject.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_fourth.*

class FourthActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieListAdapter: MovieListAdapter
    private var categoryList: MutableList<String> = mutableListOf()
    private var missCategory = true
    private lateinit var movieList: LiveData<List<Movie>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        movieListAdapter = MovieListAdapter()
        rcvResult.apply {
            layoutManager = LinearLayoutManager(this@FourthActivity)
            adapter = movieListAdapter
        }

        val sharedPreferences = getSharedPreferences("SEARCH_MOVIE", MODE_PRIVATE)
        val searchCategory =
            sharedPreferences.getString("CATEGORY", getString(R.string.movie_categories))
        val searchMovieName = sharedPreferences.getString("MOVIE_NAME", "")

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.allCategories.observe(this, { category ->
            category?.let {
                categoryList.add(getString(R.string.movie_categories))
                it.forEach { cate ->
                    categoryList.add(cate.categoryName)
                }
                val arrayAdapter =
                    ArrayAdapter(this, R.layout.spinner_item, categoryList)
                activityFourthSpnCategory.adapter = arrayAdapter
                for (i in 0 until categoryList.size) {
                    if (categoryList[i] == searchCategory) {
                        activityFourthSpnCategory.setSelection(i)
                    }
                }
            }
        })
        btnSearch.setOnClickListener {
            onSearchForResult()
        }
        activityFourthEdtMovieName.setText(searchMovieName)
    }

    private fun onSearchForResult() {
        val searchCategory = activityFourthSpnCategory.selectedItem.toString()
        val searchMovie = activityFourthEdtMovieName.text.trim().toString()
        if (searchCategory != getString(R.string.movie_categories)) {
            if (searchMovie == "") {
                movieList = movieViewModel.searchMoviesByCategory(searchCategory)
                missCategory = false
            } else {
                movieList = movieViewModel.searchResult(searchMovie, searchCategory)
                missCategory = false
            }
        } else {
            if (searchMovie == "") {
                movieList = movieViewModel.allMovies()
                missCategory = false
            } else {
                missCategory = true
                movieListAdapter.submitList(null)
                Toast.makeText(this, R.string.missing_category, Toast.LENGTH_SHORT).show()
            }
        }
        if (!missCategory) {
            movieList.observe(this, {
                if (it.isEmpty()) {
                    (it as MutableList).clear()
                    Toast.makeText(this, R.string.no_result, Toast.LENGTH_SHORT).show()
                }
                movieListAdapter.submitList(it)
            })
        }
    }

    override fun onStop() {
        super.onStop()
        val sharedPreferences = getSharedPreferences("SEARCH_MOVIE", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("CATEGORY", activityFourthSpnCategory.selectedItem.toString())
        editor.putString("MOVIE_NAME", activityFourthEdtMovieName.text.trim().toString())
        editor.apply()
    }

}