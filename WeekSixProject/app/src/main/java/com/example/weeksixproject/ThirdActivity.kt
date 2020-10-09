package com.example.weeksixproject

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.weeksixproject.entity.Movie
import com.example.weeksixproject.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.allCategories.observe(this, { category ->
            // Update the cached copy of the words in the adapter.
            category?.let {
                val categoryName = mutableListOf<String>()
                it.forEach { cate ->
                    categoryName.add(cate.categoryName)
                }
                val arrayAdapter =
                    ArrayAdapter(this, R.layout.spinner_item, categoryName)
                activityThirdSpnCategory.adapter = arrayAdapter
            }
        })
        btnAddMovieName.setOnClickListener {
            if (edtMovieName.text.trim() != "") {
                var movie: Movie = Movie(
                    movieName =
                    edtMovieName.text.trim().toString(),
                    category =
                    activityThirdSpnCategory.selectedItem.toString()
                )
                movieViewModel.insertMovie(movie)
                Toast.makeText(this, R.string.add_success, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, R.string.please_input_movie_name, Toast.LENGTH_SHORT).show()
            }
        }
        btnFinish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}