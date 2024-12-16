package com.example.tastecode.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "favourite_recipes")
data class Recipe(
    @PrimaryKey
    var id: String = "",
    var author: String? = null,
    var description: String? = null,
    var difficult: String? = null,
    var dish_type: String? = null,
    var image: String? = null,
    var ingredients: List<String> = emptyList(),
    var maincategory: String? = null,
    var name: String? = null,
    var nutrients: Map<String, String>? = null,
    var rattings: Int? = null,
    var serves: Int? = null,
    var steps: List<String> = emptyList(),
    var subcategory: String? = null,
    var times: Map<String, String>? = null,
    var url: String? = null,
    var vote_count: Int? = null
)





