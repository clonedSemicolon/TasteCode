package com.example.tastecode.data

import android.content.Context
import android.util.Log
import com.example.tastecode.security.JwtService
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.tastecode.security.PasswordService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Database {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun addUser(userId: String, firstName: String, lastName: String, email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val user = User(firstName, lastName, email, PasswordService.hashPassword(password))

        database.child("users").child(userId).setValue(user)
            .addOnSuccessListener {
                Log.d("DatabaseOperations", "User added successfully")
                onSuccess()
            }
            .addOnFailureListener { exception ->
                Log.e("DatabaseOperations", "Failed to add user", exception)
                onFailure("Failed to add user: ${exception.message}")
            }
    }

    fun loginUser(username: String, password: String, context: Context, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        database.child("users").orderByChild("email").equalTo(username).get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val userSnapshot = snapshot.children.firstOrNull()
                    val storedPassword = userSnapshot?.child("password")?.value as? String
                    if (PasswordService.verifyPassword(password, storedPassword.orEmpty())) {
                        val jwtService = JwtService(context,"1A6Y36H6L2", "tastecode")
                        val token = jwtService.generateToken(username)

                        GlobalScope.launch {
                            jwtService.saveToken(token)
                        }
                        onSuccess()
                    } else {
                        val message = "Invalid password!"
                        onFailure(message)
                    }
                } else {
                    val message = "Username not found!"
                    onFailure(message)
                }
            }
            .addOnFailureListener { exception ->
                val message = "Error connecting to database!"
                onFailure("Error connecting to database: ${exception.message}")
            }
    }
}

