package com.example.weeksixproject.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.weeksixproject.entity.Movie

class UtilDiffCallBack : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.movieId == newItem.movieId
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.movieName == newItem.movieName
    }
}