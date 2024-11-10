import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.tastecode.business.route.Screen
import com.example.tastecode.ui.theme.Poppins

@Composable
fun LoginScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Hello,",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.Black,
            ),
            modifier = Modifier.align(Alignment.Start)
        )
        
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Welcome Back!",
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
            value = "",
            onValueChange = {},
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

        Text(
            text = "Password",
            style = TextStyle(
                fontSize = 12.sp,
                color = Color(0xFF121212),
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins
            ),
            modifier = Modifier.align(Alignment.Start)
        )

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Enter Password", color = Color(0xffD9D9D9)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(8.dp)
        )

        Text(
            text = "Forgot Password?",
            color = Color(0xFFFFA726), // Orange color
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.End)
                .padding(vertical = 8.dp)
                .clickable {
                    /* Handle forgot password */
                    navHostController.navigate(Screen.ForgotPasswordScreen.route) {
                        popUpTo(Screen.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Handle sign in */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF129575)) // Green color
        ) {
            Text(
                text = "Sign In",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Filled.ArrowForward, contentDescription = null, tint = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))



        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = "Don’t have an account? Sign up",
            color = Color(0xFFFFA726), // orange color
            modifier = Modifier.clickable { /* Handle sign-up */
                navHostController.navigate(Screen.UserRegistrationScreen.route) {
                    popUpTo(Screen.UserRegistrationScreen.route) {
                        inclusive = true
                    }
                }
            },
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    LoginScreen(navHostController = fakeNavController())
}



