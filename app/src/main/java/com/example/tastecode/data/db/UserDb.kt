package com.example.tastecode.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.tastecode.data.User

@Database(entities = [User::class], version = 1)
abstract class UserDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

        companion object{
            private var userDatabase: UserDataBase? = null
            private var recipeDatabase:UserDataBase?=null

            @Synchronized
            fun getUserDatabase(context: Context): UserDataBase {
                if (userDatabase == null) {
                    userDatabase = Room.databaseBuilder(context,
                        UserDataBase::class.java,
                        "user.db").build()
                }
                return userDatabase!!
            }

            @Synchronized
            fun getFavRecipeDatabase(context: Context): UserDataBase {
                if (recipeDatabase == null) {
                    recipeDatabase = Room.databaseBuilder(context,
                        UserDataBase::class.java,
                        "favourite_recipes.db").build()
                }
                return recipeDatabase!!
            }
        }
    }

