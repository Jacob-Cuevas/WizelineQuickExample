package com.example.wizelinetechtest.data.api

import com.example.wizelinetechtest.data.db.MovieDao
import com.example.wizelinetechtest.data.model.Movie
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Repository that handles the logic to retrieve data from database or the web service.
 * @param api Interface that will help us to retrieve data from the web service.
 * @param movieDao Interface used to get data from the local database.
 */
class MovieRepository @Inject constructor(
    private val api: MovieApi,
    private val movieDao: MovieDao
) {

    /**
     * Function used to get the list of movies, either from the local database or from the remote web service.
     * The logic applied is:
     * Check first if the movies are available from the database, if not, they must be retrieved from the remot API.
     * @param apiKey API key used to authenticate us and be able to request data to the remote API.
     * @return A "Single" object that contains a list of Movie.
     */
    fun getMovies(apiKey: String): Single<List<Movie>> {
        return movieDao.getMovies().flatMap { movies ->
            if(movies.isEmpty()){
                api.getNowPlayingMovies(apiKey).map { response ->
                    movieDao.insertMovies(response.results)
                    response.results
                }
            } else {
                Single.just(movies)
            }
        }
    }

    /**
     * Function that will get a specific Movie from the local database.
     * @param id ID of the movie that will be retrieved.
     * @return A "Single" object that contains the Movie object.
     */
    fun getMovieById(id: Int): Single<Movie> {
        return movieDao.getMovieById(id)
    }

}