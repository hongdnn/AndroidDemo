package com.example.weeksixproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weeksixproject.dao.CategoryDAO
import com.example.weeksixproject.dao.MovieDAO
import com.example.weeksixproject.entity.Category
import com.example.weeksixproject.entity.Movie
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Movie::class, Category::class], version = 1)
public abstract class MovieRoomDatabase : RoomDatabase() {

    abstract fun movieDAO(): MovieDAO
    abstract fun categoryDAO(): CategoryDAO

    companion object {
        @Volatile
        private var movieRoomDatabase: MovieRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): MovieRoomDatabase {
            val tempInstance = movieRoomDatabase
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieRoomDatabase::class.java,
                    "movie_database"
                ).build()
                movieRoomDatabase = instance
                return instance
            }
        }
    }

}