package com.example.tastecode.security

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import kotlinx.coroutines.flow.first
import java.util.Date


val Context.dataStore: androidx.datastore.core.DataStore<Preferences> by preferencesDataStore(name = "auth_data")

class JwtService(
    private val context: Context,
    private val secret: String,
    private val issuer: String
) {

    private val algorithm = Algorithm.HMAC256(secret)

    private val TOKEN_KEY = stringPreferencesKey("auth_token")

    fun generateToken(userId: String): String {
        val expirationTime = Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME)
        return JWT.create()
            .withIssuer(issuer)
            .withSubject(userId)
            .withIssuedAt(Date())
            .withExpiresAt(expirationTime)
            .sign(algorithm)
    }

    fun validateToken(token: String): String? {
        return try {
            val verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
            val decodedJWT = verifier.verify(token)
            decodedJWT.subject
        } catch (e: JWTVerificationException) {
            null
        }
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun getToken(): String? {
        val preferences = context.dataStore.data.first()
        return preferences[TOKEN_KEY]
    }

    companion object {
        private const val TOKEN_EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000 // 1 Week
    }
}
