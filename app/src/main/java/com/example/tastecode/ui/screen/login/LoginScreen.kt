import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.tastecode.R
import com.example.tastecode.business.route.Screen
import com.example.tastecode.ui.components.button.LoginButton
import com.example.tastecode.ui.theme.Poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navHostController: NavHostController) {

    var password = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }

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
            value = email.value,
            onValueChange = { email.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(8.dp),
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color(0xFF129575),
                focusedBorderColor = Color(0xFF129575)
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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
            value = password.value,
            onValueChange = { password.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(8.dp),
            maxLines = 1,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color(0xFF129575),
                focusedBorderColor = Color(0xFF129575)
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation =  PasswordVisualTransformation()
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
                    navHostController.navigate(Screen.ForgotPasswordScreen.route)

                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LoginButton(
            username = email.value,
            password = password.value,
            text = stringResource(id = R.string.login),
            navHostController)

        Spacer(modifier = Modifier.height(24.dp))

        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = "Don’t have an account? Sign up",
            color = Color(0xFFFFA726), // orange color
            modifier = Modifier.clickable { /* Handle sign-up */
                navHostController.navigate(Screen.UserRegistrationScreen.route)
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



