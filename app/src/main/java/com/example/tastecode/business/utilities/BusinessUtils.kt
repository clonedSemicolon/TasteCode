package com.example.tastecode.business.utilities

import androidx.room.TypeConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


object BusinessUtils {

    fun <T> CoroutineScope.executeInBackground(
        backgroundTask: suspend () -> T,
        uiTask: (T) -> Unit
    ) {
        this.launch {
            val result = withContext(Dispatchers.IO) {
                backgroundTask()
            }
            uiTask(result)
        }
    }
}


object Constants{
    const val SECRET_KEY = "1A6Y36H6L2"
    const val JWT_ISSUER = "tastecode"
}


class Converters {

    private  val KEY_VALUE_SEPARATOR = "->"
    private  val ENTRY_SEPARATOR = "||"

    @TypeConverter
    fun fromList(value : List<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<List<String>>(value)


    @TypeConverter
    fun mapToString(map: Map<String, String>): String {
        return map.entries.joinToString(separator = ENTRY_SEPARATOR) {
            "${it.key}$KEY_VALUE_SEPARATOR${it.value}"
        }
    }

    @TypeConverter
    fun stringToMap(string: String): Map<String, String> {
        return string.split(ENTRY_SEPARATOR).map {
            val (key, value) = it.split(KEY_VALUE_SEPARATOR)
            key to value
        }.toMap()
    }
}
