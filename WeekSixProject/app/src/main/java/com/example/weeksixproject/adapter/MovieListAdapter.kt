package com.example.weeksixproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.RecyclerView
import com.example.weeksixproject.R
import com.example.weeksixproject.entity.Movie
import kotlinx.android.synthetic.main.item_result.view.*
import java.util.concurrent.Executors


class MovieListAdapter : androidx.recyclerview.widget.ListAdapter<Movie, MovieListAdapter.ViewHolder>(
    AsyncDifferConfig.Builder(UtilDiffCallBack())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            itemView.txtMovieName.text = movie.movieName
        }
    }

}





