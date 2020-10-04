package com.example.weekfiveproject

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_item.*
import kotlinx.android.synthetic.main.activity_item.view.*
import kotlinx.android.synthetic.main.menu_item.*
import kotlinx.android.synthetic.main.menu_item.view.*

class ItemActivity : AppCompatActivity() {
    private var currentPrice: Float = 0F
    private lateinit var fruitManager: FruitManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        fruitManager = FruitManager()
        val fruitTitleAdapter = FruitTitleAdapter(onItemTitleClick).apply {
            submitList(fruitManager.fruitList)
        }
        rcvFruitTitle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = fruitTitleAdapter
        }

        intent?.let {
            val bundle = intent.extras
            bundle
        }?.let {
            Glide.with(this)
                .load(it.getString("image"))
                .into(activity_item_ivItem)
            activity_item_tvSale.text = it.getString("title")
            activity_item_tvTitle.text = it.getString("title")
            activity_item_tvDescription.text = it.getString("description")
            currentPrice = it.getFloat("currentPrice")
            btnConfirm.text = "$currentPrice$"

        }

        ivBack.setOnClickListener {
            this.onBackPressed()
        }

        btnMinus.setOnClickListener {
            var quantity = tvQuantity.text.toString().toInt()
            if (quantity > 1) {
                tvQuantity.text = (--quantity).toString()
                btnConfirm.text = "${Math.round(currentPrice * quantity * 10.0) / 10.0}$"
            }
        }

        btnAdd.setOnClickListener {
            var quantity = tvQuantity.text.toString().toInt()
            tvQuantity.text = (++quantity).toString()
            btnConfirm.text = "${Math.round(currentPrice * quantity * 10.0) / 10.0}$"
        }

    }

    private var onItemTitleClick: (position: Int) -> Unit = { position ->
        println("position: $position")
        var fruit = fruitManager.fruitList[position]
        Glide.with(this)
            .asBitmap()
            .load(fruit.image)
            .into(activity_item_ivItem)

        activity_item_tvTitle.text = fruit.title
        activity_item_tvDescription.text = fruit.description
        activity_item_tvSale.text = fruit.title
        btnConfirm.text = "$${fruit.currentPrice}"
        tvQuantity.text = "1"
    }
}