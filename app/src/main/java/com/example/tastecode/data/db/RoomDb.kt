package com.example.tastecode.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.tastecode.data.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

        companion object{
            private var database: AppDatabase? = null

            @Synchronized
            fun getDatabase(context: Context): AppDatabase {
                if (database == null) {
                    database = Room.databaseBuilder(context,
                        AppDatabase::class.java,
                        "user.db").build()
                }
                return database!!
            }
        }
    }

