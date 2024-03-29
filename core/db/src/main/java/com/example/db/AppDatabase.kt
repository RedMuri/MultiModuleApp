package com.example.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.db.dao.UsersDao
import com.example.db.models.UserDbModel

@Database(entities = [UserDbModel::class], version = 7, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "redmuri_app.db"
        private val LOCK = Any()

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let { return it }
            synchronized(LOCK) {
                INSTANCE?.let { return it }
                val db =
                    Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                INSTANCE = db
                return db
            }
        }

        const val USERS_TABLE_NAME = "users"
    }

    abstract fun usersDao(): UsersDao
}