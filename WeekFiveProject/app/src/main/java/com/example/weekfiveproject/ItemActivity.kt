package com.example.weekfiveproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_item.*
import kotlin.math.roundToInt

class ItemActivity : AppCompatActivity() {
    private var currentPrice: Float = 0F
    private lateinit var fruitManager: FruitManager

    companion object {
        const val FRUIT_KEY = "fruit"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        fruitManager = FruitManager()
        intent?.let {
            val json = intent.getStringExtra(FRUIT_KEY)
            val fruit = Gson().fromJson(json, Fruit::class.java)
            fruit
        }?.let {
            initView(it)
        }

        ivBack.setOnClickListener {
            this.onBackPressed()
        }

        btnMinus.setOnClickListener {
            var quantity = tvQuantity.text.toString().toInt()
            if (quantity > 1) {
                tvQuantity.text = (--quantity).toString()
                btnConfirm.text =
                    ((currentPrice * quantity * 10.0).roundToInt() / 10.0).toString() + getString(R.string.currency)
            }
        }

        btnAdd.setOnClickListener {
            var quantity = tvQuantity.text.toString().toInt()
            tvQuantity.text = (++quantity).toString()
            btnConfirm.text =
                ((currentPrice * quantity * 10.0).roundToInt() / 10.0).toString() + getString(R.string.currency)
        }

    }

    private var onItemTitleClick: (position: Int) -> Unit = { position ->
        println("position: $position")
        val fruit = fruitManager.fruitList[position]
        Glide.with(this)
            .load(fruit.image)
            .circleCrop()
            .into(activity_item_ivItem)

        activity_item_tvTitle.text = fruit.title
        activity_item_tvDescription.text = fruit.description
        activity_item_tvSale.text = fruit.title
        btnConfirm.text = getString(R.string.currency) + fruit.currentPrice
        tvQuantity.text = "1"
    }

    fun initView(fruit: Fruit){
        Glide.with(this)
            .load(fruit.image)
            .circleCrop()
            .into(activity_item_ivItem)
        activity_item_tvSale.text = fruit.title
        activity_item_tvTitle.text = fruit.title
        activity_item_tvDescription.text = fruit.description
        currentPrice = fruit.currentPrice
        btnConfirm.text = currentPrice.toString() + getString(R.string.currency)

        val fruitTitleAdapter = FruitTitleAdapter(fruit.id - 1, onItemTitleClick).apply {
            submitList(fruitManager.fruitList)
        }
        rcvFruitTitle.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = fruitTitleAdapter
            scrollToPosition(fruit.id - 1)
        }
    }
}