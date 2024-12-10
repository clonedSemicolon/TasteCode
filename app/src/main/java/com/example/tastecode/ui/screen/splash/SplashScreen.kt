package com.example.tastecode.ui.screen.splash

import SharedData
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.tastecode.R
import com.example.tastecode.business.route.Screen
import com.example.tastecode.business.utilities.BusinessUtils.executeInBackground
import com.example.tastecode.business.utilities.Constants
import com.example.tastecode.data.db.UserDataBase
import com.example.tastecode.security.JwtService
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {


    val context = LocalContext.current
    val jwtService = JwtService(context,Constants.SECRET_KEY, Constants.JWT_ISSUER)
    val db = UserDataBase.getUserDatabase(context)



    LaunchedEffect(Unit) {
        delay(2000) // 2 seconds delay
        this.executeInBackground({
            val userToken = jwtService.getToken()
            userToken?.let {
                SharedData.userData = db.userDao().getUser()
            }
            return@executeInBackground userToken
        },{
            if(it == null){
                navHostController.navigate(Screen.LoginScreen.route) {
                    popUpTo(Screen.SplashScreen.route) { inclusive = true }
                }
            }else{
                navHostController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.SplashScreen.route) { inclusive = true }
                }
            }
        })

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.splash_bg),
                contentScale = ContentScale.FillBounds
            )
    ) {
        // Image at the top center
        Image(
            painter = painterResource(id = R.drawable.splash_icon),
            contentDescription = "Splash Image",
            modifier = Modifier
                .width(200.dp)
                .height(100.dp)
                .align(Alignment.TopCenter)
                .padding(top = 100.dp)
        )

        // Title just below the image
        Text(
            text = "TasteCode",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 220.dp)
        )

        // Big title at the bottom center
        Text(
            text = "Get Cooking",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 120.dp)
        )

        // Subtitle at the bottom center
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Simple way to find Tasty Recipe",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF99989b),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 90.dp)
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview(){
//    SplashScreen()
}