package com.example.tastecode.data

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.util.Log

class Database {

    private lateinit var database: DatabaseReference

    fun initializeDbRef() {
        database = FirebaseDatabase.getInstance().reference
    }

    fun addUser(userId: String, firstName: String, lastName: String, username: String, email: String, password: String) {
        val user = User(firstName, lastName, email, username, password)

        database.child("users").child(userId).setValue(user)
            .addOnSuccessListener {

                Log.d("DatabaseOperations", "User added successfully")
            }
            .addOnFailureListener {

                Log.e("DatabaseOperations", "Failed to add user", it)
            }
    }
}