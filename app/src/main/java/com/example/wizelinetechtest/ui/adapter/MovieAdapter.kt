package com.example.wizelinetechtest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wizelinetechtest.data.model.Movie
import com.example.wizelinetechtest.databinding.ItemMovieBinding

/**
 * Adapter used to display a list of Movie items in a RecyclerView.
 * @param onMovieClick A lambda function that is invoked when a movie items is clicked.
 */
class MovieAdapter(
    private val onMovieClick: (Movie) -> Unit
) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    /**
     * Function called when RecyclerView needs a new MovieViewHolder to represent a Movie item.
     * This method inflates the layout for each movie item and creates a new MovieViewHolder.
     *
     * @param parent The ViewGroup where the new view will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new `MovieViewHolder` that holds the view for a Movie item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    /**
     * Function called by RecyclerView to display the data at the specified position.
     * This function retrieves the Movie data at the given position and binds it to the MovieViewHolder.
     * @param holder The MovieViewHolder that should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }

    /**
     * ViewHolder class for displaying a Movie item.
     * This class holds the view for a single Movie item and binds its data to the views within that layout.
     * It also handles click events for the Movie item.
     * @property binding The view binding object for the item layout: item_movie.xml.
     */
    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Function that binds the Movie data to the views, placing the title, rating, poster image in the views. It also
         * sets up a click listener that invokes the onMovieClick lambda with the current Movie when the item is clicked.
         * @param movie The Movie object containing the data to be displayed.
         */
        fun bind(movie: Movie) {
            binding.titleTextView.text = movie.title
            binding.ratingTextView.text = movie.rating.toString()

            Glide.with(binding.posterImageView.context)
                // Set the URL provided by the owner of the remote API to get the binary that represents the image to display.
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .into(binding.posterImageView)

            binding.root.setOnClickListener {
                onMovieClick(movie)
            }
        }
    }
}