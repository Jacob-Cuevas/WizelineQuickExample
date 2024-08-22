package com.example.wizelinetechtest.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.wizelinetechtest.databinding.ActivityMovieDetailBinding
import com.example.wizelinetechtest.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity that displays detailed information about a selected Movie item.
 * @property binding The view binding object for the activity's layout: activity_movie_detail.xml.
 * @property viewModel The MovieViewModel instance that provides the Movie details for the UI. This viewModel
 * is injected using Hilt and tied to the activity's lifecycle.
 */
@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel: MovieViewModel by viewModels()

    /**
     * Function called when the activity is first created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding object and indicate the layout that must be displayed as content.
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the Movie ID passed via the Intent to this activity.
        val movieId = intent.getIntExtra("movie_id", -1)

        // Trigger the function to get the whole Movie item data.
        viewModel.getMovieDetail(movieId)

        // Create an observe to listen when the movieDetail variable has content (list<Movie>) to update the UI.
        viewModel.movieDetail.observe(this) { movie ->  // Observa los cambios en el LiveData
            movie?.let {
                binding.titleTextView.text = movie.title
                binding.descriptionTextView.text = movie.description
                binding.releaseDateTextView.text = movie.releaseDate
                binding.durationTextView.text = "${movie.duration} min"
                binding.genresTextView.text = "N/A"//movie.genres.joinToString(", ")

                // Load the image using Glide, using the URL where the owner of the remote API indicated to
                // dowload the images.
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .into(binding.posterImageView)
            }
        }

        // Handle the back button click to finish the activity.
        binding.backButton.setOnClickListener {
            finish()
        }
    }
}
