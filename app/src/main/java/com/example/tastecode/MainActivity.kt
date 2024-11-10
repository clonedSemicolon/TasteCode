package com.example.tastecode

import LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tastecode.components.TastCodeAppTest
import com.example.tastecode.ui.screen.registration.RegistrationScreen
import com.example.tastecode.ui.theme.TastecodeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            /*TastecodeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Taste-Code",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }*/
            //Guna Testing
            //RegistrationScreen()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    LoginScreen()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TastecodeTheme {
        Greeting("Android")
    }
}