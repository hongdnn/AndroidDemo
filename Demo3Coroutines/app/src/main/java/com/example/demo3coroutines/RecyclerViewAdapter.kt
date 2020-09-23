package com.example.demo3coroutines

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(context: Context, list: ArrayList<Int>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val context: Context = context
    private val dataList: ArrayList<Int> = list

    private inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var message: TextView = itemView.findViewById(R.id.btnItem)
        fun bind(position: Int) {
            message.text = dataList[position].toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)
    }

    fun changeBackgroundColour(viewHolder: RecyclerView.ViewHolder, second: Int) {
        val itemButton: Button = viewHolder.itemView?.findViewById(R.id.btnItem)
        if ((second % 2 == 0 && itemButton.text.toString()
                .toInt() % 2 == 0) || (second % 2 != 0 && itemButton.text.toString()
                .toInt() % 2 != 0)
        ) {
            itemButton.setBackgroundColor(Color.BLUE)
        } else {
            itemButton.setBackgroundColor(Color.WHITE)
        }
    }

}