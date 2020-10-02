package com.example.weekfiveproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_item.*

class ItemActivity : AppCompatActivity() {
    private var currentPrice: Float = 0F
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        intent?.let {
            val bundle = intent.extras
            bundle
        }?.let {
            Glide.with(this)
                .load(it.getString("image"))
                .into(activity_item_ivItem)
            if (it.getString("sale") != null) {
                activity_item_tvSale.text = it.getString("sale")
                activity_item_tvSale.visibility = View.VISIBLE
            }
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

}