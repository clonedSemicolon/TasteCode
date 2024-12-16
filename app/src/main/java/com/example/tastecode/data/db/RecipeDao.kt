package com.example.tastecode.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tastecode.data.Recipe

@Dao
interface RecipeDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(recipe: Recipe)

    @Query("SELECT * FROM favourite_recipes")
    suspend fun getFavouriteRecipes(): List<Recipe>?

}