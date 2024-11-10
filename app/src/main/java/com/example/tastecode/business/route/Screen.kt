package com.example.tastecode.business.route

sealed class Screen(val route: String) {
    data object SplashScreen : Screen("splash_screen")
    data object LoginScreen : Screen("login_screen")
    data object UserRegistrationScreen : Screen("user_registration_screen")
    data object ForgotPasswordScreen : Screen("forgot_password_screen")
}
