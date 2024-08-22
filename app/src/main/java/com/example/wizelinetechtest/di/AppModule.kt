package com.example.wizelinetechtest.di

import android.app.Application
import androidx.room.Room
import com.example.wizelinetechtest.data.api.MovieApi
import com.example.wizelinetechtest.data.api.MovieRepository
import com.example.wizelinetechtest.data.db.MovieDao
import com.example.wizelinetechtest.data.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Dagger Hilt module that provides the necessary dependencies in the app.
 * This object defines methods that create and provide instance of some dependencies like: MovieApi, MovieDatabase,
 * MovieDao and MovieRepository. These dependencies are injected into different parts of the app.
 * @Module Indicates this class is a Dagger Hilt module.
 * @InstallIn (SingletonComponent::class) Specifies that all of these dependencies will have a singleton lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Function that provides an instance of the MovieApi to interacting with the remote API, creating and configuring an
     * instance of Retrofit to make HTTP requests.
     * @return MovieApi instance.
     */
    @Provides
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    /**
     * Provides an instance of the MovieDatabase that handles the local data persistance, creating and configuring an instance
     * of Room to manage the SQLite database that will store the data. The database is created using the app context
     * and use the "movie_db" name to create the database.
     * @param app Application context that will be used to create the database instance.
     * @return An instance of the MovieDatabase object.
     */
    @Provides
    fun provideMovieDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(app, MovieDatabase::class.java, "movie_db").build()
    }

    /**
     * Function that provides access to the MovieDao object for interacting with the database.
     * @param db Instance of the MovieDatabase from wich the DAO is obtained.
     * @return an instance of the MovieDao interface to interact with the database.
     */
    @Provides
    fun provideMovieDao(db: MovieDatabase): MovieDao {
        return db.movieDao()
    }

    /**
     * Function that provides an instance of the MovieRepository class to be able to interact
     * with the database or retrieve data from the remote API.
     * @param api An instance of the MovieApi that will handle the interaction with the remote API.
     * @param movieDao An instance of the MovieDao that will interact with the database.
     * @return An Instance of the MovieRepository to manage the business logic (database and/or remote API).
     */
    @Provides
    fun provideMovieRepository(api: MovieApi, movieDao: MovieDao): MovieRepository {
        return MovieRepository(api, movieDao)
    }

}