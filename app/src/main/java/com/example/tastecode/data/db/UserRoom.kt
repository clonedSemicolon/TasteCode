package com.example.tastecode.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tastecode.data.User


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertUser(user: com.example.tastecode.data.User)

    @Query("SELECT * FROM users WHERE userId = :userId")
     suspend fun getUserById(userId: String): User?

    @Query("DELETE FROM users")
     suspend fun clearUsers()

    @Query("SELECT * FROM users ORDER BY userId ASC LIMIT 1")
    suspend fun getUser(): User?
}