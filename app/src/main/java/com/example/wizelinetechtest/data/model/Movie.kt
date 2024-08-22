package com.example.wizelinetechtest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Data class that represents the Movie object.
 * This class represents the entity Movie in the database and also has some properties that are mapped to the JSON response
 * object that will be retrieved from the "movie/now_playing" endpoint.
 * @property id Int ID that represents the Movie item. This property is also the primary key of the Movie table.
 * @property title String name of the Movie.
 * @property posterPath String that represents the URL path where the Movie image can be downloaded. Also, it is mapped to
 * the same property returned in the JSON response. It can be null.
 * @property rating Float that represents the rating of the Movie.
 * @property duration Integer of the movie duration.
 * @property releaseDate String that represents the release date of the movie. It can be null.
 * @property genres String that contains a list of IDs concatenated by a comma that represents the genres of the movie. It can be null.
 * @property description String that contains a small overview of the movie.
 */
@Entity(tableName = "Movie")
data class Movie (
    @PrimaryKey(true) val id: Int,
    val title: String,
    @SerializedName("poster_path") val posterPath: String?,
    val rating: Float,
    val duration: Int,
    val releaseDate: String?,
    val genres: String?,
    @SerializedName("overview") val description: String?
)