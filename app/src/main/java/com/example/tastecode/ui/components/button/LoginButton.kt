package com.example.tastecode.ui.components.button


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tastecode.data.Database


@Composable
fun LoginButton(
    username: String,
    password: String,
    text: String
) {
    val context = LocalContext.current
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showFailureDialog by remember { mutableStateOf(false) }
    var failureMessage by remember { mutableStateOf("") }

    Button(
        onClick = {
            val database = Database()
            database.loginUser(
                username = username,
                password = password,
                context = context,
                onSuccess = {
                    showSuccessDialog = true
                },
                onFailure = { error ->
                    showFailureDialog = true
                    failureMessage = error
                }
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF129575))
    ) {

        if (showSuccessDialog) {
            AlertDialog(
                onDismissRequest = { showSuccessDialog = false },
                confirmButton = {
                    TextButton(onClick = { showSuccessDialog = false }) {
                        Text("OK")
                    }
                },
                title = { Text("Login Successful") },
                text = { Text("You have logged in successfully!") }
            )
        }

        if (showFailureDialog) {
            AlertDialog(
                onDismissRequest = { showFailureDialog = false },
                confirmButton = {
                    TextButton(onClick = { showFailureDialog = false }) {
                        Text("OK")
                    }
                },
                title = { Text("Login Failed") },
                text = { Text(failureMessage) }
            )
        }

        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(Icons.Filled.ArrowForward, contentDescription = null, tint = Color.White)
    }
}


