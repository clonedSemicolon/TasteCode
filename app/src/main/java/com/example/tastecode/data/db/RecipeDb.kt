package com.example.tastecode.data.db


import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.TypeConverters
import com.example.tastecode.business.utilities.Converters
import com.example.tastecode.data.Recipe


@Database(entities = [Recipe::class], version = 1)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao():RecipeDao

    companion object{
        private var recipeDatabase:RecipeDatabase?=null

        @Synchronized
        fun getFavRecipeDatabase(context: Context): RecipeDatabase {
            if (recipeDatabase == null) {
                recipeDatabase = Room.databaseBuilder(context,
                    RecipeDatabase::class.java,
                    "favourite_recipes.db").build()
            }
            return recipeDatabase!!
        }
    }
}

