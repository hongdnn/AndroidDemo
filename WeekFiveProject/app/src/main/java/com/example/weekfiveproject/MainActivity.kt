package com.example.weekfiveproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var fruitListAdapter: FruitListAdapter
    private lateinit var fruitManager: FruitManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fruitManager = FruitManager()
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val width: Int = metrics.widthPixels

        fruitListAdapter = FruitListAdapter(width / 3, {}) { position ->
            //navigateToViewPager(position)
        }
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = fruitListAdapter
        }
        fruitListAdapter.submitList(fruitManager.fruitList)
    }
}