package com.example.wizelinetechtest.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.wizelinetechtest.data.model.Movie

/**
 * Class used by MovieAdapter class to calculate the difference between two lists of movies and efficiently
 * update the RecyclerView only when necessary.
 */
class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {

    /**
     * This function checks whether the two Movies have the same ID, which is used as the
     * unique identifier for a Movie object.
     *
     * @param oldItem The old Movie object.
     * @param newItem The new Movie object.
     * @return a Boolean value (true) if the two items have the same ID, (false) otherwise.
     */
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * This function checks whether all the properties of the two movies are equal.
     *
     * @param oldItem The old Movie object.
     * @param newItem The new Movie object.
     * @return A Boolean (true) if the contents of the two items are the same, (false) otherwise.
     */
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}