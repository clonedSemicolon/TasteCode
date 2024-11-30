package com.example.tastecode.data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(val firstName: String? = null, val lastName: String? = null, val email: String? = null, val password: String? = null) {
}