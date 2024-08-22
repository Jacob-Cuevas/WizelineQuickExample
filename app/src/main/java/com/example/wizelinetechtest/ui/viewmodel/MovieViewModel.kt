package com.example.wizelinetechtest.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wizelinetechtest.data.api.MovieRepository
import com.example.wizelinetechtest.data.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * ViewModel that manages UI of the activities and the async operations to retrive data.
 * @property repository The MovieRepository instance used to fetch data (remote API and local database).
 */
@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {

    init {
        Log.d("MovieViewModel", "MovieViewModel initialized with repository: $repository")
    }

    // API key used for requests to the remote API.
    private val apiKey = "c0823934438075d63f1dbda4023e76fc"

    // LiveData for holding the list of Movie items.
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    // LiveData for holding the details of a specific Movie item.
    private val _movieDetail = MutableLiveData<Movie>()
    val movieDetail : LiveData<Movie> = _movieDetail

    /**
     * This function calls the getMovies method of the MovieRepository to retrieve the list of Movie items.
     * The result is observed on the main thread and posted to the _movies LiveData, which can then be observed
     * by the UI to update the list of Movie items. If an error occurs during the fetch, it is logged.
     */
    fun getMovies(){
        repository.getMovies(apiKey)
            // Indicate this HTTP request must be invoked using an I/O thread
            .subscribeOn(Schedulers.io())
            // And post the result in the main thread.
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movieList ->
                _movies.value = movieList

            }, { throwable ->
                Log.e("MovieViewModel", "Error loading list of Movie items", throwable)
            })
    }

    /**
     * This function calls the getMovieById method of the MovieRepository to retrieve the whole Movie item
     * identified by the given movieId value. The result is observed on the main thread and posted
     * to the _movieDetail LiveData, which can then be observed by the UI to display the Movie item.
     */
    fun getMovieDetail(movieId: Int) {
        repository.getMovieById(movieId)
            // Invoke the database query using an I/O thread
            .subscribeOn(Schedulers.io())
            // And observe the result on the main thread.
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movie ->
                _movieDetail.value = movie
            }, { throwable ->
                Log.e("MovieViewModel", "Error loading Movie item", throwable)
            })
    }

}