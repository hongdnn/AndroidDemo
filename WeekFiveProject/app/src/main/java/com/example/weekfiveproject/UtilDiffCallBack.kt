package com.example.weekfiveproject

import androidx.recyclerview.widget.DiffUtil

class UtilDiffCallBack : DiffUtil.ItemCallback<Fruit>() {
    override fun areItemsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.description == newItem.description &&
                oldItem.image == newItem.image &&
                oldItem.initialPrice == newItem.initialPrice &&
                oldItem.description == newItem.description &&
                oldItem.currentPrice == newItem.currentPrice
    }
}