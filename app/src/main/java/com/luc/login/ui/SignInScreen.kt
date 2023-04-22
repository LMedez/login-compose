package com.luc.login.ui

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luc.login.R
import com.luc.login.domain.model.User
import com.luc.login.presentation.viewmodel.AuthViewModel
import com.luc.login.ui.common.LoginButton
import com.luc.login.ui.common.LoginTextField
import com.luc.login.ui.theme.MainApplicationTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun SignInScreen(navigateToSignUp: () -> Unit, navigateToHome: (User) -> Unit) {

    val userAuthViewModel: AuthViewModel = getViewModel()
    val userCredentialState by userAuthViewModel.userCredentialsStatus.collectAsState()
    val userState by userAuthViewModel.user.collectAsState()

    var enabledButton by remember {
        mutableStateOf(false)
    }

    enabledButton = userCredentialState.isEmailValid && userCredentialState.isPasswordValid

    userState.user?.let {
        navigateToHome(it)
    }
    userState.error?.let {
        Log.d("tests", it)
    }

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
            stringResource(R.string.email_example),
            onValueChange = {
                userAuthViewModel.validateEmail(it)
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        LoginTextField(
            Modifier.fillMaxWidth(),
            stringResource(id = R.string.your_password),
            stringResource(R.string.password_example),
            PasswordVisualTransformation(),
            onValueChange = {
                userAuthViewModel.validatePassword(it)
            }
        )

        SignInDefaultButton(
            enabled = enabledButton,
            onClick = {
                userAuthViewModel.signInDefault(
                    userCredentialState.email,
                    userCredentialState.password
                )
            })

        Spacer(modifier = Modifier.weight(1f))

        SignUpDefault { navigateToSignUp() }
    }
}

@Composable
fun SignInDefaultButton(enabled: Boolean = false, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LoginButton(
            onClick = onClick,
            text = "Continue",
            modifier = Modifier.fillMaxWidth(),
            paddingValues = PaddingValues(top = 30.dp),
            enabled = enabled
        )
        OrDivider()
        SignUpBrandButton(text = "Sign up with Google")
        Spacer(modifier = Modifier.height(20.dp))
        SignUpBrandButton(text = "Sign up with Facebook", painterResource(id = R.drawable.facebook))
    }
}

@Composable
fun OrDivider() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 30.dp, bottom = 30.dp)
    ) {
        Divider(
            thickness = 1.dp,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.2f)
        )
        Text(
            text = "or",
            modifier = Modifier.padding(horizontal = 12.dp),
            color = MaterialTheme.colorScheme.primary
        )
        Divider(
            thickness = 1.dp,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.2f)
        )
    }
}

@Composable
fun SignUpBrandButton(
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
fun SignUpDefault(onClick: () -> Unit) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Don't have any account?",
            modifier = Modifier.padding(end = 6.dp),
            color = MaterialTheme.colorScheme.primary
        )
        TextButton(onClick = { onClick() }) {
            Text(text = "Sign Up")
        }
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
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

// Previews
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode")
@Composable
private fun NightPreview() {
    MainApplicationTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SignInScreen({},{})
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
private fun LightPreview() {
    MainApplicationTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SignInScreen({},{})
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

private fun credentialValidator(email: String = "", password: String = "") =
    email.length in 4..9 && password.length > 8


