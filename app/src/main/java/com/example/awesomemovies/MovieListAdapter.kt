package com.example.awesomemovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val moviesList = mutableListOf<MovieItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieItemViewHolder) {
            holder.bind(moviesList[position])
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun setData(movieList: List<MovieItem>) {
        this.moviesList.clear()
        this.moviesList.addAll(movieList)
        notifyDataSetChanged()
    }

}


class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var movieThumbnail: ImageView
    var movieTitle: TextView
    var movieYear: TextView
    var type: TextView

    init {
        movieThumbnail = itemView.findViewById(R.id.thumbnail)
        movieTitle = itemView.findViewById(R.id.movieTitle)
        movieYear = itemView.findViewById(R.id.movieReleaseYear)
        type = itemView.findViewById(R.id.type)
    }

    fun bind(movieItem: MovieItem) {
        Glide.with(movieThumbnail).load(movieItem.poster).into(movieThumbnail)
        movieTitle.text = movieItem.title
        movieYear.text = movieItem.year
        type.text = movieItem.type

    }
}