package com.example.weekfiveproject

import android.content.Intent
import android.graphics.Insets
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.view.WindowMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var fruitListAdapter: FruitListAdapter
    private lateinit var fruitManager: FruitManager
    private var width: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fruitManager = FruitManager()
        println("w: $width")
        width = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics: WindowMetrics = windowManager.currentWindowMetrics
            val insets: Insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val metrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(metrics)
            metrics.widthPixels
        }
        fruitListAdapter = FruitListAdapter(width / 2) { position ->
            navigateToItemActivity(position)
        }
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = fruitListAdapter
        }
        fruitListAdapter.submitList(fruitManager.fruitList)

    }

    fun navigateToItemActivity(position: Int) {
        val intent = Intent(this, ItemActivity::class.java)
        val fruit = fruitManager.fruitList[position]
        val bundle = Bundle()
        bundle.putInt("id", fruit.id)
        bundle.putString("title", fruit.title)
        bundle.putString("description", fruit.description)
        bundle.putString("image", fruit.image)
        bundle.putString("sale", fruit.sale)
        bundle.putFloat("currentPrice", fruit.currentPrice)
        bundle.putFloat("initialPrice", fruit.initialPrice)

        intent.putExtras(bundle)
        startActivity(intent)
    }


}

