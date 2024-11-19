package com.example.tastecode.ui.components.button

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tastecode.data.Database
import com.example.tastecode.data.User

@Composable
fun RegisterButton(
    id: String? = null,
    user: User? = null,
    text: String,
    onClick: () -> Unit = {}
) {
    val context = LocalContext.current

    Button(
        onClick = {
            val db = Database()

            user?.let {
                db.addUser(
                    userId = id ?: "",
                    firstName = it.firstName ?: "",
                    lastName = it.lastName ?: "",
                    email = it.email ?: "",
                    password = it.password ?: ""
                )

                Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()
            }


            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF129575))
    ) {
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
