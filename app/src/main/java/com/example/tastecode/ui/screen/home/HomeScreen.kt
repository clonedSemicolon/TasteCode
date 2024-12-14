import com.example.tastecode.ui.screen.loading.LoadingScreen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tastecode.R
import com.example.tastecode.business.route.Screen
import com.example.tastecode.components.MyTextField
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    // Coroutine scope to launch drawer state changes
    val scope = rememberCoroutineScope()
    val recipeViewModel: RecipeViewModel =
        viewModel(factory = RecipeViewModelFactory(RecipeRepository()))
    val recipes by recipeViewModel.recipes.observeAsState(emptyList())


    // State to manage search query and filters
    val searchQuery = remember { mutableStateOf("") }

    val selectedCategory = remember { mutableStateOf<String?>(null) }
    val selectedDifficulty = remember { mutableStateOf<String?>(null) }
    val selectedTime = remember { mutableStateOf<String?>(null) }
    val selectedRating = remember { mutableStateOf<String?>(null) }


    // Function to filter recipes based on search and filters
    val filteredRecipes = recipes.filter { recipe ->
        (searchQuery.value.isEmpty() || recipe.name?.contains(searchQuery.value, ignoreCase = true) == true) &&
                (selectedCategory.value.isNullOrEmpty() || selectedCategory.value == recipe.dish_type) &&
                (selectedDifficulty.value.isNullOrEmpty() || selectedDifficulty.value == recipe.difficult) &&
                (selectedTime.value.isNullOrEmpty() || selectedTime.value == recipe.times?.get("total")) &&
                (selectedRating.value.isNullOrEmpty() || selectedRating.value?.toIntOrNull() == recipe.rattings)
    }





    var showFilterScreen = remember { mutableStateOf(false) }

    if (filteredRecipes.isNotEmpty()) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    drawerContentColor = Color(0xFF129575),
                    modifier = Modifier.fillMaxWidth(0.7f),
                ) {
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                Icons.Filled.Logout,
                                contentDescription = "Menu",
                                tint = Color(0xFF129575)
                            )
                        },
                        label = {
                            Text(
                                "Logout",
                                color = Color(0xFF129575),
                                fontWeight = FontWeight(600)
                            )
                        },
                        onClick = {
                            scope.launch {
                                navController.navigate(Screen.LoginScreen.route)
                                SharedData.userData = null
                            }
                        },
                        selected = false
                    )
                }
            }
        ) {
            Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                Spacer(modifier = Modifier.height(32.dp))

                // Header with menu icon to open the drawer
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = Modifier.padding(vertical = 12.dp),
                        onClick = {
                            scope.launch {
                                if (drawerState.isOpen) {
                                    drawerState.close()
                                } else {
                                    drawerState.open()
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Sidebar",
                            tint = Color(0xFF129575)
                        )
                    }

                    // Profile Button
                    IconButton(
                        modifier = Modifier.padding(vertical = 12.dp),
                        onClick = { /* Your profile navigation logic */ }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Profile",
                            tint = Color(0xFF129575)
                        )
                    }
                }

                // Greeting Text
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
                ){
                    Text(
                        text = "Hello, ${SharedData.userData?.firstName}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black,
                        )
                    )
                }

                // Search Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),  // Vertical padding to ensure better spacing
                    verticalAlignment = Alignment.CenterVertically // Center align both items vertically
                ) {




                    OutlinedTextField(
                        value = searchQuery.value,
                        onValueChange = {
                            searchQuery.value = it
                        },
                        label = { Text(text = "Search Recipe") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(horizontal = 8.dp),
                        shape = RoundedCornerShape(8.dp),
                        keyboardOptions = KeyboardOptions.Default,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color(0xFF129575),
                            focusedBorderColor = Color(0xFF129575)
                        )
                    )

//                    OutlinedTextField(
//                        value = searchQuery.value,
//                        onValueChange = { searchQuery.value = it },
//                        label = { Text("Search Recipes") },
//                        modifier = Modifier
//                            .fillMaxWidth(0.8f) // Fill 80% of the width for the text field
//                            .height(42.dp) // Set a smaller height for the search box
//                            .background(Color.Transparent, shape = MaterialTheme.shapes.medium)
//                            .padding(horizontal = 16.dp),
//                        singleLine = true,
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            unfocusedBorderColor = Color(0xFF129575),
//                            focusedBorderColor = Color(0xFF129575)
//                        )
//                    )

                    IconButton(
                        onClick = { showFilterScreen.value = true },
                        modifier = Modifier.padding(start = 8.dp) // Add padding between the button and text field
                    ) {
                        Icon(
                            imageVector = Icons.Filled.FilterList,
                            contentDescription = "Filter",
                            tint = Color(0xFF129575)
                        )
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))

                // Recipe List
                RecipeList(
                    navHostController = navController,
                    recipeList = filteredRecipes,
                    viewModel = recipeViewModel
                )
            }
        }

        AnimatedVisibility(
            visible = showFilterScreen.value,
            enter = slideInHorizontally(initialOffsetX = { it }) + fadeIn(),
            exit = slideOutHorizontally(targetOffsetX = { it }) + fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight()
                    .background(Color.White)
            ) {


                FilterColumn(
                    selectedCategory,
                    selectedDifficulty,
                    selectedTime,
                    selectedRating,
                    showFilterScreen
                )

                IconButton(
                    modifier = Modifier.align(Alignment.TopEnd).padding(12.dp),
                    onClick = {
                        showFilterScreen.value = false },
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }
        }
    } else {
        LoadingScreen()
    }


}


