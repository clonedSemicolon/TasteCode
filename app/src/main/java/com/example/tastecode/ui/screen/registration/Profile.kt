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


@Composable
fun ProfileScreen() {
    var isDarkTheme by remember { mutableStateOf(false) }


    // Apply Theme Based on Toggle
    MaterialTheme(
        colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top App Bar or Back Arrow
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
                            painter = painterResource(R.drawable.backarrow),
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
                        painter = painterResource(R.drawable.ic_profilepicture),
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
                    text = "Robi",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "8967452743",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "robi123@gmail.com",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )


                Spacer(modifier = Modifier.height(24.dp))


                // Profile Options
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
                        .padding(20.dp)
                ) {
                    // Order History Section
                    ExpandableSection(
                        title = "Order History",
                        content = {
                            Column {
                                Text("- Order #1: Paneer Tikka - Delivered", fontSize = 14.sp)
                                Text("- Order #2: Chicken masala - Shipped", fontSize = 14.sp)
                                Text("- Order #3: Turkey - Delivered", fontSize = 14.sp)
                                Text("- Order #4: Pizza - In Transit", fontSize = 14.sp)
                            }
                        }
                    )


                    // Shipping Address Section
                    ExpandableSection(
                        title = "Shipping Address",
                        content = {
                            EditableShippingAddress()
                        }
                    )


                    // Create Request Section
                    ExpandableSection(
                        title = "Create Request",
                        content = {
                            Text("You can create a new service request here.", fontSize = 14.sp)
                        }
                    )


                    // Privacy Policy Section
                    ExpandableSection(
                        title = "Privacy Policy",
                        content = {
                            Text("Read our terms and privacy policies.", fontSize = 14.sp)
                        }
                    )


                    // Settings Section
                    ExpandableSection(
                        title = "Settings",
                        content = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Enable Dark Mode",
                                    modifier = Modifier.weight(1f),
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Switch(
                                    checked = isDarkTheme,
                                    onCheckedChange = { isDarkTheme = it }
                                )
                            }
                        }
                    )


                    // Log out Section
                    ProfileOptionItem(
                        title = "Log out",
                        icon = R.drawable.logout,
                        showDivider = false
                    )
                }
            }
        }
    }
}


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
                    .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                    .padding(8.dp),
                textStyle = LocalTextStyle.current.copy(color = Color.Black) // Text color
            )


            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { isEditing = false }, // Save and stop editing
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
fun ExpandableSection(title: String, content: @Composable () -> Unit) {
    var expanded by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { expanded = !expanded },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.orderhistory2), // Replace with your icon
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
                painter = painterResource(if (expanded) R.drawable.downarrow else R.drawable.uparrow), // Expand/Collapse icons
                contentDescription = if (expanded) "Collapse" else "Expand",
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified // Set the color to transparent
            )
        }
        if (expanded) {
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}


@Composable
fun ProfileOptionItem(title: String, icon: Int, showDivider: Boolean) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = title,
                modifier = Modifier.size(24.dp)
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
            Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f), thickness = 1.dp)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}
