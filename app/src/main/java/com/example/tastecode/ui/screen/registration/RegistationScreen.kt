package com.example.tastecode.ui.screen.registration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tastecode.R
import com.example.tastecode.components.NormalTextComponent
import androidx.compose.ui.unit.dp
import com.example.tastecode.components.HeadingTextComponent
import com.example.tastecode.components.MyTextField

@Composable
fun RegistrationScreen() {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(29.dp)
    ){
        Column(modifier=Modifier.fillMaxSize()) {
            NormalTextComponent(value = stringResource(id = R.string.gk))
            HeadingTextComponent(value = stringResource(id = R.string.create_account))
            Spacer(modifier = Modifier.height(20.dp))
            MyTextField(
                labelValue= stringResource(id = R.string.first_name),
                painterResource(id = R.drawable.img)
            )
            MyTextField(
                labelValue= stringResource(id = R.string.last_name),
                painterResource = painterResource(id = R.drawable.img)
            )
            MyTextField(
                labelValue= stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.img)
            )
            MyTextField(
                labelValue= stringResource(id = R.string.pass_word),
                painterResource = painterResource(id = R.drawable.img)
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfRegistrationScreen(){
    RegistrationScreen()

}
