/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.luc.login.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.luc.login.R
import com.luc.login.presentation.viewmodel.UserViewModel
import com.luc.login.ui.common.CommonLoginButton
import com.luc.login.ui.common.LogoImage
import com.luc.login.ui.theme.MainApplicationTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigateToSignIn: () -> Unit = {},
) {
    val viewModel: UserViewModel = getViewModel()
    val userState by viewModel.state.collectAsState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        val (cornerBox, logo, texts, button) = createRefs()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .clip(RoundedCornerShape(bottomStart = 140.dp, bottomEnd = 140.dp))
                .background(MaterialTheme.colorScheme.primary)
                .constrainAs(cornerBox) {

                }
        )
        LogoImage(Modifier.constrainAs(logo) {
            top.linkTo(cornerBox.bottom)
            bottom.linkTo(cornerBox.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, size = DpSize(70.dp, 70.dp))

        Column(
            Modifier
                .padding(25.dp)
                .constrainAs(texts) {
                    top.linkTo(logo.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.app_title),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = stringResource(id = R.string.app_subtitle1),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
            )
        }

        CommonLoginButton(modifier = Modifier
            .constrainAs(button) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            onClick = { navigateToSignIn() },
            "Get started for free",
            PaddingValues(bottom = 50.dp)
        )
    }
}

// Previews
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode")
@Composable
private fun NightPreview() {
    MainApplicationTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MainScreen()
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
private fun LightPreview() {
    MainApplicationTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MainScreen()
        }
    }
}
