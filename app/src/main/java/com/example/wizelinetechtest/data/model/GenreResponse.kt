package com.example.wizelinetechtest.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class that represents the response object of the "genre/movie/list" API request.
 * It returns a JSON object that will be converted to a list of Genre items.
 * @property genres List of Genre items that represent the genres that can be applied to a movie. This property
 * is mapped to the "genres" attribute of the JSON response.
 */
data class GenreResponse (
    @SerializedName("genres") val genres: List<Genre>
)