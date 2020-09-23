package com.example.demo2coroutines

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(context: Context, list: ArrayList<String>, private val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val context: Context = context
    private val dataList: ArrayList<String> = list

    private inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var message: TextView = itemView.findViewById(R.id.tvItem)
        fun bind(position: Int) {
            itemView.setOnClickListener {
                onClick(position)
            }
            message.text = dataList[position]
            itemView.context.getString(R.string.app_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)
    }


}