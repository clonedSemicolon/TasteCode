package com.example.tastecode.ui.screen.profile

import androidx.compose.animation.animateContentSize
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
fun EditableShippingAddress() {
    var address by remember { mutableStateOf("123 Main Street, Springfield, IL 62704") }
    var isEditing by remember { mutableStateOf(false) }

    Column {
        if (isEditing) {
            // Editable TextField
            TextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Edit Address") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface
                )

            )

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { isEditing = false }, // Save the updated address
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Save")
            }
        } else {
            // Display Saved Address
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = address,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = { isEditing = true } // Enable editing mode
                ) {
                    Text("Edit")
                }
            }
        }
    }
}

@Composable
fun ChangePassword() {
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

    Column {
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

        // Save Button
        Button(
            onClick = { /* Handle password change logic */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save")
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
        // Back Button
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

        // Title
        Text(
            text = "Profile",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.Center) // Center the text
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
fun ProfileOptionItem(
    title: String,
    icon: Int,
    showDivider: Boolean
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { /* Add action here, e.g., navigate or log out */ },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(icon), // Ensure the icon resource exists
                contentDescription = title,
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f)
            )
        }
        if (showDivider) {
            Divider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                thickness = 1.dp
            )
        }
    }
}


@Composable
fun ProfileScreen() {
    var isDarkTheme by remember { mutableStateOf(false) }

    // Apply Greenish Theme Based on Toggle
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
                    ExpandableSection(
                        title = "Favorite Recipes",
                        icon = R.drawable.favouriterecipes, // Replace with your icon
                        content = {
                            Column {
                                Text("- Butter Chicken", fontSize = 18.sp)
                                Text("- Veg Biryani", fontSize = 18.sp)
                                Text("- Margherita Pizza", fontSize = 18.sp)
                                Text("- Chocolate Cake", fontSize = 18.sp)
                            }
                        }
                    )

                    // Change Password Section
                    ExpandableSection(
                        title = "Change Password",
                        icon = R.drawable.changepass, // Replace with your icon
                        content = { ChangePassword() }
                    )
                }
            }
        }
    }
}




@Composable
fun ExpandableSection(title: String, icon: Int, content: @Composable () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(icon), // Use dynamic icon
                contentDescription = title,
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = painterResource(if (expanded) R.drawable.uparrow2 else R.drawable.downarrow),
                contentDescription = if (expanded) "Collapse" else "Expand",
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified
            )
        }
        if (expanded) {
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}
