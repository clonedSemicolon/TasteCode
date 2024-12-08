package com.example.tastecode.business.utilities

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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


}