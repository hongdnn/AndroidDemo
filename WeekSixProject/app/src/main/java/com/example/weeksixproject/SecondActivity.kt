package com.example.weeksixproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weeksixproject.entity.Category
import com.example.weeksixproject.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        btnAddCategory.setOnClickListener {
            if (edtCategory.text.trim() != "") {
                var category: Category = Category(categoryName = edtCategory.text.trim().toString())
                movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
                movieViewModel.viewModelScope.launch(Dispatchers.IO) {
                    var result = movieViewModel.insertCategory(category)
                    withContext(Dispatchers.Main){
                        var textResult =
                            if (result) getString(R.string.add_success) else "${category.categoryName} was added"
                        Toast.makeText(this@SecondActivity, textResult, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, R.string.please_input_category, Toast.LENGTH_SHORT).show()
            }
        }
        btnAddMovie.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }
    }
}