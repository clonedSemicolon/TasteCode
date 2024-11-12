package com.example.tastecode.ui.screen.fogotpassword

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tastecode.R
import com.example.tastecode.ui.theme.Poppins

@Composable
fun ForgotPasswordScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = stringResource(id = R.string.forgot_password),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.forgot_password_msg),
            fontWeight = FontWeight.W500,
            fontSize = 12.sp,
            color = Color(0xFF121212),
        )


        Spacer(modifier = Modifier.height(72.dp))


        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(text = "Email Address") },
            modifier = Modifier
                .width(350.dp)
                .height(60.dp),
            shape = RoundedCornerShape(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF129575)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .width(350.dp)
                .height(60.dp)
        ) {
            Text(text = "Submit")
        }


    }
}
