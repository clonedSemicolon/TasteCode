package com.example.tastecode.ui.screen.fogotpassword

import UserActionButton
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tastecode.R
import com.example.tastecode.business.route.Screen
import com.example.tastecode.ui.components.button.LoginButton
import com.example.tastecode.ui.theme.Poppins
import fakeNavController

@Composable
fun ForgotPasswordScreen(navHostController: NavHostController) {
    var email = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Forgot Password?",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.Black,
            ),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "No Worries!",
            style = TextStyle(
                fontSize = 18.sp,
                color = Color(0xff121212),
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier.align(Alignment.Start)
        )

        Text(
            text = "We can set it again for you.",
            style = TextStyle(
                fontSize = 18.sp,
                color = Color(0xff121212),
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(32.dp))


        Text(
            text = "Email",
            style = TextStyle(
                fontSize = 12.sp,
                color = Color(0xFF121212),
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins
            ),
            modifier = Modifier.align(Alignment.Start)
        )

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = {
                Text("Enter Email",
                    color = Color(0xffD9D9D9),
                ) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Spacer(modifier = Modifier.height(16.dp))

        UserActionButton(text = "Submit")
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordPreview() {
    ForgotPasswordScreen(navHostController = fakeNavController())
}
