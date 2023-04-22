package com.luc.login.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.luc.login.ui.common.LoginTextField
import com.luc.login.ui.common.LogoImage
import com.luc.login.R
import com.luc.login.domain.model.User
import com.luc.login.presentation.viewmodel.AuthViewModel
import com.luc.login.ui.common.LoginButton
import org.koin.androidx.compose.getViewModel

@Composable
fun SignUpScreen(navigateToSignIn: () -> Unit, navigateToHome: (User) -> Unit) {
    val userAuthViewModel: AuthViewModel = getViewModel()
    val userCredentialState by userAuthViewModel.userCredentialsStatus.collectAsState()
    val userState by userAuthViewModel.user.collectAsState()

    userState.user?.let {
        navigateToHome(it)
    }
    userState.error?.let {
        Log.d("tests", it)
    }

    var enabledButton by remember {
        mutableStateOf(false)
    }

    enabledButton = userCredentialState.isEmailValid && userCredentialState.isPasswordValid

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        SignInBack(onClick = { navigateToSignIn() })
        LogoImage(
            size = DpSize(35.dp, 35.dp),
            modifier = Modifier.padding(top = 50.dp, bottom = 25.dp)
        )
        Text(
            text = "Let's create your profile",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        LoginTextField(
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            label = stringResource(R.string.full_name),
            placeHolder = "James Rodriguez",
            onValueChange = {
                userAuthViewModel.validateUserName(it)
            }
        )
        LoginTextField(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            label = stringResource(id = R.string.email),
            placeHolder = stringResource(id = R.string.email_example),
            onValueChange = {
                userAuthViewModel.validateEmail(it)
            }
        )
        LoginTextField(
            Modifier.fillMaxWidth(),
            label = stringResource(id = R.string.choose_password),
            placeHolder = stringResource(id = R.string.password_example),
            onValueChange = {
                userAuthViewModel.validatePassword(it)
            }
        )

        LoginButton(
            Modifier.fillMaxWidth(),
            onClick = { userAuthViewModel.signUpDefault(userCredentialState.email, userCredentialState.password) },
            text = "Create account",
            paddingValues = PaddingValues(top = 30.dp),
            enabled = enabledButton
        )
    }
}

@Composable
fun SignInBack(onClick: () -> Unit) {
    TextButton(onClick = onClick, modifier = Modifier.offset(x = (-15).dp)) {
        Icon(
            Icons.Rounded.KeyboardArrowLeft,
            modifier = Modifier
                .size(20.dp),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "Sign In",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

