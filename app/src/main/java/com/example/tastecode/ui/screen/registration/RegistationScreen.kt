package com.example.tastecode.ui.screen.registration

import UserActionButton
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.tastecode.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tastecode.business.route.Screen
import com.example.tastecode.components.MyTextField
import com.example.tastecode.components.PasswordTextField
import com.example.tastecode.data.User
import com.example.tastecode.ui.components.button.RegisterButton
import fakeNavController


@Composable
fun RegistrationScreen(navHostController: NavHostController) {

    var termsAccepted by remember { mutableStateOf(false) }
    var firstName = remember { mutableStateOf("") }
    var lastName = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = R.string.create_account),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.Black,
            ),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = R.string.create_account_msg),
            style = TextStyle(
                fontSize = 12.sp,
                color = Color(0xff121212),
                fontWeight = FontWeight.W400,
            ),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(24.dp))

        MyTextField(
            textValue = firstName,
            labelValue = stringResource(id = R.string.first_name),
            painterResource = painterResource(id = R.drawable.img)
        )

        MyTextField(
            textValue = lastName,
            labelValue = stringResource(id = R.string.last_name),
            painterResource = painterResource(id = R.drawable.img)
        )

        MyTextField(
            textValue = email,
            labelValue = stringResource(id = R.string.email),
            painterResource = painterResource(id = R.drawable.img)
        )

        PasswordTextField(
            textValue = password,
            labelValue = stringResource(id = R.string.pass_word),
        )

        PasswordTextField(
            textValue = password,
            labelValue = stringResource(id = R.string.confirm_password)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                checked = termsAccepted,
                onCheckedChange = { termsAccepted = it }
            )
            Text(text = "I accept the terms and conditions",
                color = Color(0xFFFFA726), // Orange color
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clickable {
                        /* Handle forgot password */
                        navHostController.navigate(Screen.ForgotPasswordScreen.route) {
                            popUpTo(Screen.LoginScreen.route) {
                                inclusive = true
                            }
                        }
                    })
        }

        var user: User = User(firstName.value, lastName.value, email.value, password.value)
        var id:String = java.util.UUID.randomUUID().toString()
        RegisterButton(id ,user, text = stringResource(id = R.string.register))
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(id = R.string.already_member))
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color(0xFFFFA726))) {
                        append(stringResource(id = R.string.login))
                    }
                },
                modifier = Modifier.clickable {
                    navHostController.navigate(Screen.LoginScreen.route) {
                        popUpTo(Screen.UserRegistrationScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewOfRegistrationScreen() {
    RegistrationScreen(fakeNavController())
}
