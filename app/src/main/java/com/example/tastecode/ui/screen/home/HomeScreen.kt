import com.example.tastecode.ui.screen.loading.LoadingScreen

import android.annotation.SuppressLint
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tastecode.business.route.Screen
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
    val recipeViewModel: RecipeViewModel = viewModel(factory = RecipeViewModelFactory(RecipeRepository()))
    val recipes by recipeViewModel.recipes.observeAsState(emptyList())


    // State to manage search query and filters
    val searchQuery = remember { mutableStateOf("") }
    val selectedCategory = remember { mutableStateOf<String?>(null) }
    val selectedDifficulty = remember { mutableStateOf<String?>(null) }

    var showFilterSheet = remember { mutableStateOf(false) }

    // Function to filter recipes based on search and filters
    val filteredRecipes = recipes.filter {
        (searchQuery.value.isEmpty() || it.name?.contains(searchQuery.value, ignoreCase = true) == true) &&
                (selectedCategory.value?.let { category -> it.dish_type == category } ?: true) &&
                (selectedDifficulty.value?.let { difficulty -> it.difficult == difficulty } ?: true)
    }

    val bottomSheetState = rememberModalBottomSheetState()


    val openBottomSheet = {
        scope.launch { bottomSheetState.show() }
        showFilterSheet.value = true
    }
    val closeBottomSheet = {
        scope.launch { bottomSheetState.hide() }
        showFilterSheet.value = false
    }

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
                            Icon(Icons.Filled.Logout, contentDescription = "Menu", tint = Color(0xFF129575))
                        },
                        label = { Text("Logout", color = Color(0xFF129575), fontWeight = FontWeight(600)) },
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
                Text(
                    text = "Hello, ${SharedData.userData?.firstName}",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black,
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Search Bar
                Row(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = searchQuery.value,
                        onValueChange = { searchQuery.value = it },
                        label = { Text("Search Recipes") },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .background(Color.Transparent, shape = MaterialTheme.shapes.medium)
                            .padding(horizontal = 16.dp),
                        singleLine = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color(0xFF129575),
                            focusedBorderColor = Color(0xFF129575)

                        )
                    )

                    IconButton(
                        onClick = {
                            showFilterSheet.value = true
                            openBottomSheet()
                        },
                        modifier = Modifier.padding(start = 8.dp)
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

                ModalBottomSheet(
                    sheetState = bottomSheetState,
                    content = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(16.dp)
                                .animateContentSize()
                        // Align the dialog at the bottom
                        ) {
                            // Close Button
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                IconButton(onClick = { openBottomSheet() }) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = "Close",
                                        tint = Color(0xFF129575)
                                    )
                                }
                            }

                            // Category Filter
                            Text("Category", style = MaterialTheme.typography.titleMedium)
                            DropdownMenu(
                                expanded = selectedCategory.value != null,
                                onDismissRequest = { selectedCategory.value = null },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                DropdownMenuItem(onClick = {
                                    selectedCategory.value = "Dessert"
                                }, text = { Text("Dessert") })
                                DropdownMenuItem(onClick = {
                                    selectedCategory.value = "MainCourse"
                                }, text = { Text("Main Course") })
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Difficulty Filter
                            Text("Difficulty", style = MaterialTheme.typography.titleMedium)
                            DropdownMenu(
                                expanded = selectedDifficulty.value != null,
                                onDismissRequest = { selectedDifficulty.value = null },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                DropdownMenuItem(onClick = {
                                    selectedDifficulty.value = "Easy"
                                }, text = { Text("Easy") })
                                DropdownMenuItem(onClick = {
                                    selectedDifficulty.value = "Medium"
                                }, text = { Text("Medium") })
                                DropdownMenuItem(onClick = {
                                    selectedDifficulty.value = "Hard"
                                }, text = { Text("Hard") })
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            // Apply Button
                            Button(
                                onClick = {
                                    // Apply filters logic here
                                    closeBottomSheet()
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Apply Filters")
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Cancel Button to dismiss Dialog
                            TextButton(
                                onClick = {
                                    closeBottomSheet()
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Cancel")
                            }
                        }}, onDismissRequest = {
                            closeBottomSheet()
                    })





        }
    } else {
        LoadingScreen()
    }
}


