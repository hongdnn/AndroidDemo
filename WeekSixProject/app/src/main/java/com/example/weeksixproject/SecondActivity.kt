package com.example.weeksixproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.weeksixproject.entity.Category
import com.example.weeksixproject.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        btnAddCategory.setOnClickListener {
            if (edtCategory.text.trim() != "") {
                var category: Category = Category(edtCategory.text.trim().toString())
                movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
                var result = movieViewModel.insertCategory(category)
                var a = if (result) "Add success" else "${category.categoryName} was added"
                println("RESULT: $result")
                Toast.makeText(this, a, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please input category", Toast.LENGTH_SHORT).show()
            }
        }
        btnAddMovie.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }
    }
}