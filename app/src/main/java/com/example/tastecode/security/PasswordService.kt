package com.example.tastecode.security

import org.mindrot.jbcrypt.BCrypt

object PasswordService {

    fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    fun verifyPassword(enteredPassword: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(enteredPassword, hashedPassword)
    }
}