import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tastecode.business.route.Screen
import com.example.tastecode.ui.screen.fogotpassword.ForgotPasswordScreen
import com.example.tastecode.ui.screen.loading.LoadingScreen
import com.example.tastecode.ui.screen.profile.ProfileScreen
import com.example.tastecode.ui.screen.registration.RegistrationScreen
import com.example.tastecode.ui.screen.splash.SplashScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(Screen.UserRegistrationScreen.route) {
            RegistrationScreen(navController)
        }
        composable(Screen.ForgotPasswordScreen.route) {
            ForgotPasswordScreen(navController)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(Screen.RecipeDetailsScreen.route) {
            RecipeDetailsScreen(navController)
        }
        composable(Screen.FetchingDataScreen.route) {
            LoadingScreen()
        }
        composable(Screen.ProfileScreen.route){
            ProfileScreen(navController)
        }

    }
}
