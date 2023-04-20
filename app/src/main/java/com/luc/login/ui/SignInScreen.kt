package com.luc.login.ui

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luc.login.R
import com.luc.login.ui.common.LoginButton
import com.luc.login.ui.common.LoginTextField
import com.luc.login.ui.theme.MainApplicationTheme

@Composable
fun SignInScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderLogo()

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(id = R.string.app_subtitle2),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(70.dp))

        LoginTextField(
            Modifier.fillMaxWidth(),
            stringResource(id = R.string.email),
            "example.name@domain.com",
            onValueChange = {

            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        LoginTextField(
            Modifier.fillMaxWidth(),
            stringResource(id = R.string.password),
            "min. 8 characters",
            PasswordVisualTransformation(),
            onValueChange = {

            }
        )
        LoginButtons()
    }
}

@Composable
fun LoginButtons() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LoginButton(
            onClick = { /*TODO*/ },
            text = "Continue",
            modifier = Modifier.fillMaxWidth(),
            paddingValues = PaddingValues(top = 30.dp)
        )
        Text(text = "or", Modifier.padding(top = 30.dp, bottom = 30.dp))
        SignUpButton(text = "Sign up with Google")
        Spacer(modifier = Modifier.height(20.dp))
        SignUpButton(text = "Sign up with Facebook", painterResource(id = R.drawable.facebook))
    }
}

@Composable
fun SignUpButton(
    text: String,
    painter: Painter = painterResource(id = com.google.firebase.firestore.R.drawable.googleg_standard_color_18),
) {
    Button(
        onClick = { /*TODO*/ },
        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.2f)),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.fillMaxWidth()
    ) {

        Icon(
            painter,
            modifier = Modifier
                .size(20.dp),
            contentDescription = "",
            tint = Color.Unspecified
        )
        Text(text = text, modifier = Modifier.padding(10.dp))
    }
}

@Composable
fun HeaderLogo() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.leaf_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(25.dp, 25.dp)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = stringResource(id = R.string.app_title),
            style = MaterialTheme.typography.displaySmall
        )
    }
}

// Previews
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode")
@Composable
private fun NightPreview() {
    MainApplicationTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SignInScreen()
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
private fun LightPreview() {
    MainApplicationTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SignInScreen()
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun TextFieldsPreview() {
    MainApplicationTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            LoginTextField(label = "", placeHolder = "")
        }
    }
}


