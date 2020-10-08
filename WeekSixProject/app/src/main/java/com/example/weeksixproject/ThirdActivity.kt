package com.example.weeksixproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weeksixproject.entity.Category
import com.example.weeksixproject.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.allCategories.observe(this, Observer { category ->
            // Update the cached copy of the words in the adapter.
            category?.let {
                var categoryName = mutableListOf<String>()
                it.forEach{ cate ->
                    categoryName.add(cate.categoryName)
                }
                var arrayAdapter =
                    ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, categoryName)
                activityThirdSpnCategory.adapter = arrayAdapter
            }
        })

    }
}