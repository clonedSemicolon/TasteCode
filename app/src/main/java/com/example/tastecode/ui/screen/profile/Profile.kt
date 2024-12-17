package com.example.tastecode.ui.screen.profile

import SharedData
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.draw.clip
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import com.example.tastecode.business.utilities.BusinessUtils.executeInBackground
import com.example.tastecode.data.Recipe
import com.example.tastecode.data.User
import com.example.tastecode.data.db.RecipeDatabase
import com.example.tastecode.data.db.UserDataBase


@Composable
fun ProfileScreen(navController: NavController) {
    val user: User? = SharedData.userData

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // User Information Section
            if (user != null) {
                UserInfoSection(
                    user = user
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Favorite Recipes Section
            ExpandableFavoriteRecipes()

            Spacer(modifier = Modifier.height(16.dp))

            // Change Password Option with Icon

        }
    }
}


@Composable
fun UserInfoSection(user: User) {
    var isEditing by remember { mutableStateOf(false) }
    var editableFirstName by remember { mutableStateOf(user.firstName ?: "") }
    var editableLastName by remember { mutableStateOf(user.lastName ?: "") }

    val maskedUserId = "****${"TasteCode98789".takeLast(4)}"

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Picture
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(MaterialTheme.colorScheme.surface, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_person_24),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(80.dp)
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f), CircleShape)
                    .padding(2.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // User Info
        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isEditing) {
                // Editable First Name
                TextField(
                    value = editableFirstName,
                    onValueChange = { editableFirstName = it },
                    label = { Text("First Name") },
                    singleLine = true,
                    modifier = Modifier
                        .padding(end = 8.dp)
                )
            } else {
                Text(
                    text = editableFirstName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            Icon(
                painter = painterResource(if (isEditing) R.drawable.baseline_check_24 else R.drawable.baseline_edit_24),
                contentDescription = if (isEditing) "Save" else "Edit",
                modifier = Modifier
                    .size(14.dp)
                    .clickable {
                        if (isEditing) {
                            // Save logic here, e.g., update the database or API
                        }
                        isEditing = !isEditing
                    },
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isEditing) {
                // Editable Last Name
                TextField(
                    value = editableLastName,
                    onValueChange = { editableLastName = it },
                    label = { Text("Last Name") },
                    singleLine = true,
                    modifier = Modifier
                        .padding(end = 8.dp)
                )
            } else {
                Text(
                    text = editableLastName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            Icon(
                painter = painterResource(if (isEditing) R.drawable.baseline_check_24 else R.drawable.baseline_edit_24),
                contentDescription = if (isEditing) "Save" else "Edit",
                modifier = Modifier
                    .size(14.dp)
                    .clickable {
                        if (isEditing) {
                            // Save logic here, e.g., update the database or API
                        }
                        isEditing = !isEditing
                    },
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Email: ${user.email}",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "User ID: $maskedUserId",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
    }
}


@Composable
fun ExpandableFavoriteRecipes() {
    var expanded by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val favouriteRecipes = remember { mutableStateOf(emptyList<Recipe>()) }

    LaunchedEffect(Unit) {
        val db = RecipeDatabase.getFavRecipeDatabase(context = context)
        executeInBackground({
            return@executeInBackground db.recipeDao().getFavouriteRecipes()
        }, {
            if (it != null) {
                favouriteRecipes.value = it
            }
        })
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
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
                painter = painterResource(R.drawable.baseline_favorite_24),
                contentDescription = "Favorite Recipes",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Favorite Recipes",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = painterResource(if (expanded) R.drawable.baseline_keyboard_arrow_up_24 else R.drawable.baseline_keyboard_arrow_down_24),
                contentDescription = if (expanded) "Collapse" else "Expand",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

        if (expanded) {
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(12.dp)
            ) {
                items(favouriteRecipes.value) { item ->
                    RecipeCard(item)
                }
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(120.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF129575).copy(0.2F))
                .padding(8.dp)
        ) {
            AsyncImage(
                model = recipe.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = recipe.name ?: "",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "By ${recipe.author}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
                Text(
                    text = "${recipe.times?.get("Cooking")}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }
        }
    }
}







