package com.example.wizelinetechtest.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class that represents the JSON response returned by the "movie/now_playing" API endpoint.
 * @property results List of Movie items that were retrieved from the API response. This field is mmaped to the
 * "results" attribute returned in the JSON response.
 */
data class MovieResponse (
    @SerializedName("results") val results: List<Movie>
)