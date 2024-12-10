package com.example.tastecode.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tastecode.R

// Greenish Color Scheme
private val LightGreenColorScheme = lightColorScheme(
    primary = Color(0xFF4CAF50), // Green primary color
    onPrimary = Color.White,
    background = Color(0xFFF0FDF4), // Light green background
    onBackground = Color(0xFF1B5E20), // Dark green for text/icons
    surface = Color.White,
    onSurface = Color(0xFF1B5E20)
)

private val DarkGreenColorScheme = darkColorScheme(
    primary = Color(0xFF2E7D32), // Darker green primary
    onPrimary = Color.White,
    background = Color(0xFF1B5E20), // Dark green background
    onBackground = Color.White,
    surface = Color(0xFF2E7D32),
    onSurface = Color.White
)

@Composable
fun ProfileScreen(navController: NavController) {
    var isDarkTheme by remember { mutableStateOf(false) }

    MaterialTheme(
        colorScheme = if (isDarkTheme) DarkGreenColorScheme else LightGreenColorScheme
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top App Bar and Profile Info
                HeaderSection()
                Spacer(modifier = Modifier.height(24.dp))

                // Profile Options
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
                        .padding(20.dp)
                ) {
                    // Favorite Recipes Section
                    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                        Text(
                            text = "Favorite Recipes",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Column {
                            Text("- Butter Chicken", fontSize = 16.sp)
                            Text("- Veg Biryani", fontSize = 16.sp)
                            Text("- Margherita Pizza", fontSize = 16.sp)
                            Text("- Chocolate Cake", fontSize = 16.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Change Password Section
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate("changePassword") }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.changepass), // Replace with your icon
                            contentDescription = "Change Password",
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Change Password",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
    ) {
        IconButton(
            onClick = { /* Handle back navigation */ },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                painter = painterResource(R.drawable.backarrow), // Replace with your back arrow icon
                contentDescription = "Back",
                tint = Color.Unspecified
            )
        }

        Text(
            text = "Profile",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.Center)
        )
    }

    // Profile Picture Section
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(MaterialTheme.colorScheme.surface, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_profilepicture), // Replace with your profile picture
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(80.dp)
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f), CircleShape)
                .padding(2.dp),
            contentScale = ContentScale.Crop
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "John Doe", // Replace with user's full name
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = "johndoe@gmail.com", // Replace with user's email
        fontSize = 18.sp,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun ChangePasswordScreen(navController: NavController) {
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.backarrow),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Change Password",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Old Password Input
        TextField(
            value = oldPassword,
            onValueChange = { oldPassword = it },
            label = { Text("Old Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface
            )
        )

        // New Password Input
        TextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text("New Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.popBackStack() }, // Navigate back to Profile Screen
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save")
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "profile") {
        composable("profile") { ProfileScreen(navController) }
        composable("changePassword") { ChangePasswordScreen(navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    AppNavigation()
}
