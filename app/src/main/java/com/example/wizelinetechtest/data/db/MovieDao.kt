package com.example.wizelinetechtest.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wizelinetechtest.data.model.Movie
import io.reactivex.rxjava3.core.Single

/**
 * DAO object that will define the functions to get data from the database.
 */
@Dao
interface MovieDao {

    /**
     * Get all Movie items from the database.
     * @return A "Single" object that contains a list of Movie items available in the database.
     */
    @Query("SELECT * FROM Movie")
    fun getMovies(): Single<List<Movie>>

    /**
     * Function used to insert a list of Movie items in the database, and replace the existing ones.
     * @param movies List of Movie items that must be saved in the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    /**
     * Function that will get a specific Movie item by an ID.
     * @param movieId ID of the Movie item that must be retrieved from the database.
     * @return A "Single" object that contains the Movie item.
     */
    @Query("SELECT * FROM Movie WHERE id= :movieId LIMIT 1")
    fun getMovieById(movieId: Int): Single<Movie>

}