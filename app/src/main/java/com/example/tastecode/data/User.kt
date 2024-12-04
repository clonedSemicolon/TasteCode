package com.example.tastecode.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId:Int = 0,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val password: String? = null
) {
}