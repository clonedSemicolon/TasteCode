package com.example.tastecode.ui.screen.aboutus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tastecode.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyListScope


// Greenish Color Scheme
private val LightGreenColorScheme = lightColorScheme(
    primary = Color(0xFF4CAF50), // Green primary color
    onPrimary = Color.White,
    background = Color(0xFFF0FDF4), // Light green background
    onBackground = Color(0xFF1B5E20), // Dark green for text/icons
    surface = Color.White,
    onSurface = Color(0xFF1B5E20)
)

@Composable
fun AboutUsPage(navController: NavController) {
    MaterialTheme(colorScheme = LightGreenColorScheme) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header Section

                // Team Image
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(MaterialTheme.colorScheme.surface, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo), // Replace with your team image
                        contentDescription = "Team Image",
                        modifier = Modifier
                            .size(100.dp)
                            .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f), CircleShape)
                            .padding(4.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // About Us Text
                Text(
                    text = "Welcome to TasteCode, your ultimate food recipe app. Our mission is to help food enthusiasts discover, save, and share delicious recipes with ease. Whether you are a professional chef or a home cook, TasteCode is here to inspire your culinary journey.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Values Section
                Text(
                    text = "Our Values",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    item {
                        ValueItem("🍴 Simplicity: We believe great recipes should be accessible to everyone.")
                    }
                    item {
                        ValueItem("🌱 Sustainability: We support eco-friendly cooking practices.")
                    }
                    item {
                        ValueItem("🤝 Community: Our platform thrives on a shared love for food.")
                    }
                    item {
                        ValueItem("🌟 Quality: We prioritize quality recipes from trusted sources.")
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))



                Text(text = "Version:1.0.0", color = MaterialTheme.colorScheme.primary)
                Text(text = "Last Update: 16 Dec, 2024", color = MaterialTheme.colorScheme.primary)

            }
        }
    }
}

@Composable
fun ValueItem(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAboutUsPage() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "aboutUs") {
        composable("aboutUs") { AboutUsPage(navController = navController) }
    }
}


