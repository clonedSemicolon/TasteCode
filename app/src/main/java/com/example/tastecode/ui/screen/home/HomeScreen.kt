import android.annotation.SuppressLint
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ViewSidebar
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tastecode.business.route.Screen
import com.example.tastecode.business.utilities.BusinessUtils
import com.example.tastecode.business.utilities.BusinessUtils.executeInBackground
import com.example.tastecode.ui.screen.loading.LoadingScreen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    // Coroutine scope to launch drawer state changes
    val scope = rememberCoroutineScope()
    val recipeViewModel:RecipeViewModel = viewModel(factory = RecipeViewModelFactory(RecipeRepository()))
    val recipes by recipeViewModel.recipes.observeAsState(emptyList())

    if(recipes.isNotEmpty()){
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                // Drawer content
                ModalDrawerSheet(
                    drawerContentColor = Color(0xFF129575),
                    modifier = Modifier
                        .fillMaxWidth(0.7f),
                ) {
                    // Here you can define the items in the side drawer
                    NavigationDrawerItem(
                        icon = {
                            Icon(Icons.Filled.Logout, contentDescription = "Menu", tint = Color(0xFF129575)) },
                        label = { Text("Logout", color = Color(0xFF129575), fontWeight = FontWeight(600)) },
                        onClick = {
                            scope.launch {
                                navController.navigate(Screen.LoginScreen.route)
                                SharedData.userData = null
                            }
                        },
                        selected = false
                    )
                    // Add more drawer items as needed
                }
            }
        ) {
            Column {
                Spacer(modifier = Modifier.height(32.dp))

                // Header with menu icon to open the drawer
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Menu button to toggle drawer
                    IconButton(
                        modifier = Modifier.padding(vertical = 12.dp),
                        onClick = {
                            // Toggle the drawer state
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

                    // Another button (e.g., Profile)
                    IconButton(
                        modifier = Modifier.padding(vertical = 12.dp),
                        onClick = {  }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Share",
                            tint = Color(0xFF129575)
                        )
                    }
                }

                Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                    Text(
                        text = "Hello, ${SharedData.userData?.firstName}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black,
                        )
                    )
                }

                RecipeList(navHostController = navController, viewModel = recipeViewModel)
            }
        }
    }else{
        LoadingScreen()
    }

}
