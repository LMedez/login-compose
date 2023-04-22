package com.luc.login.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.luc.login.R

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    paddingValues: PaddingValues,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(paddingValues),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.2f),
            disabledContentColor = MaterialTheme.colorScheme.surfaceTint
        ),
        enabled = enabled
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = text,
                modifier = Modifier
                    .padding(10.dp)
            )
            Icon(
                Icons.Rounded.KeyboardArrowRight,
                modifier = Modifier
                    .size(20.dp),
                contentDescription = "",
                tint = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceTint
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    label: String,
    placeHolder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit = {}
) {
    var name by remember {
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable { mutableStateOf(true) }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it; onValueChange(it) },
            shape = RoundedCornerShape(100),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                textColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.2f)
            ),
            placeholder = {
                Text(text = placeHolder, color = MaterialTheme.colorScheme.surfaceTint)
            },
            trailingIcon = {
                if (visualTransformation is PasswordVisualTransformation) {
                    val image = if (passwordVisible)
                        painterResource(id = R.drawable.round_visibility)
                    else painterResource(id = R.drawable.round_visibility_off)

                    val contentDescription =
                        if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = image,
                            contentDescription,
                            tint = MaterialTheme.colorScheme.surfaceTint
                        )
                    }
                }
            },
        )
    }
}

@Composable
fun LogoImage(modifier: Modifier = Modifier, size: DpSize) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(100))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(size.height * 0.35f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.leaf_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(size)
        )
    }
}