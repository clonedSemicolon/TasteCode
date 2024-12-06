package com.example.tastecode.ui.screen.loading

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tastecode.R
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen() {
    var messageIndex by remember { mutableStateOf(0) }
    val messages = listOf(
        "Fetching delicious recipes...",
        "Almost ready to cook...",
        "Preparing your culinary adventure..."
    )
    val images = listOf(
        R.drawable.cooking_1, // Replace with your image resources
        R.drawable.cooking_2,
        R.drawable.cooking_3
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            messageIndex = (messageIndex + 1) % messages.size
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = images[messageIndex]),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            BasicText(
                text = messages[messageIndex],
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            )
        }
    }
}

