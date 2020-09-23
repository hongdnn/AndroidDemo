package com.example.demo2coroutines


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections.emptyList
import java.util.concurrent.Executors


class StringListAdapter constructor(
    private val onClick: (String) -> Unit
) : ListAdapter<String, StringListAdapter.ViewHolder>(
    AsyncDifferConfig.Builder(UtilDiffCallBack())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var message: TextView = itemView.findViewById(R.id.tvItem)
        fun bind(text: String) {
            itemView.setOnClickListener {
                onClick(text)
            }
            message.text = text
            //itemView.context.getString(R.string.app_name)
        }
    }

//    override fun submitList(list: MutableList<String>?) {
//        super.submitList(list)
//    }

}


