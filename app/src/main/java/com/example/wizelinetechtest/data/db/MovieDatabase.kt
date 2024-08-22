package com.example.wizelinetechtest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wizelinetechtest.data.model.Movie

/**
 * Abstract class that represents the database connection/instance that will store the data in the app.
 * This class will use Room to create and manage a SQLite database.
 * @property entities List of entities that will create tables in the database.
 * @property version Database version that will increase on each change.
 * @property exportSchema Option that indicates if Room must create a schema file to export the database.
 */
@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    /**
     * Method that allows access to the DAO interface.
     * @return A MovieDao instance that can be used to manage the CRUD actions in the database.
     */
    abstract fun movieDao(): MovieDao
}