package com.example.weekfiveproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fruit_title_item.view.*

class FruitTitleAdapter(selected: Int, private val onItemClick: (Int) -> Unit) :
    ListAdapter<Fruit, FruitTitleAdapter.FruitTitleVH>(UtilDiffCallBack()) {
    private var selectedPosition = selected

    inner class FruitTitleVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(fruit: Fruit) {
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
                selectedPosition = adapterPosition
                notifyDataSetChanged()
            }

            itemView.btnFruitTitle.text = fruit.title
            if (this@FruitTitleVH.adapterPosition == selectedPosition) {
                itemView.btnFruitTitle.background.setTint(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.Brown
                    )
                )
            } else {
                itemView.btnFruitTitle.background.setTint(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.White
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitTitleVH {
        return FruitTitleVH(
            LayoutInflater.from(parent.context).inflate(R.layout.fruit_title_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FruitTitleVH, position: Int) {
        holder.bind(getItem(position))
    }
}