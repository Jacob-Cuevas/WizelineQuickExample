package com.example.wizelinetechtest.data.api

import com.example.wizelinetechtest.data.model.GenreResponse
import com.example.wizelinetechtest.data.model.MovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface created to define the API calls that will be used to retrieve information from
 * a test web service.
 */
interface MovieApi {

    /**
     * Get list of available movies that are playing now.
     * @param apiKey API key that is necessary to authenticate us and get data from the web service.
     * @return A "Single" object that contains a "MovieResponse" object with a list of "Movie" items.
     */
    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String): Single<MovieResponse>

    /**
     * Get the list of available genres applied to all movies.
     * @param apiKey API key that is necessary to authenticate us and get data from the web service.
     * @return a "Single" object that contains a "GenreResponse" object that represents a list of "Genre" items.
     */
    @GET("genre/movie/list")
    fun getGenres(@Query("api_key") apiKey: String): Single<GenreResponse>

}