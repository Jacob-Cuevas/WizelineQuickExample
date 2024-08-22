package com.example.wizelinetechtest.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wizelinetechtest.databinding.ActivityMainBinding
import com.example.wizelinetechtest.ui.viewmodel.MovieViewModel
import com.example.wizelinetechtest.ui.adapter.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity that displays a list of Movie items using a RecyclerView and the MoviewViewModel
 * to get the data and update the UI.
 * @property viewModel The MovieViewModel instance that provides the data to be displayed in the UI and
 * it is injected using Hilt.
 * @property binding The view binding ohject for the activity's layout: "activity_main.xml".
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    /**
     * Function invoked when this activity is first created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding object and indicate the layout that must be displayed as content.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the MovieAdapter with the data that must be displayed and creating the click listener
        // that will redirect the user to the movie detail page.
        val movieAdapter = MovieAdapter { movie ->

            val intent = Intent(this, MovieDetailActivity::class.java).apply {
                // Indicate to the activity the movie ID that was clicked.
                putExtra("movie_id", movie.id)
            }
            startActivity(intent)

        }

        // Prepare the recyclerView with a linearLayoutManager and its adapter.
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }

        // Create an observer to get notified when the list of Movie items that will be displayed in the recyclerView page is available.
        viewModel.movies.observe(this) { movies ->
            movieAdapter.submitList(movies)
        }

        // Trigger the viewModel to get the list of Movie items to render.
        viewModel.getMovies()
    }
}
