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

package com.luc.login.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.luc.login.R

private val fontRubik = FontFamily(
    Font(R.font.rubik_regular, FontWeight.Normal),
    Font(R.font.rubik_medium, FontWeight.W500),
    Font(R.font.rubik_bold, FontWeight.Bold),
)

private val fontRaleway = FontFamily(
    Font(R.font.raleway_regular),
    Font(R.font.raleway_medium, FontWeight.W500),
    Font(R.font.raleway_semi_bold, FontWeight.W600),
    Font(R.font.raleway_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val typography = typographyFromDefaults(
    titleSmall = TextStyle(fontFamily = fontRubik),
    titleMedium = TextStyle(fontFamily = fontRubik, fontWeight = FontWeight.Normal),
    titleLarge = TextStyle(fontFamily = fontRubik),
    headlineSmall = TextStyle(fontFamily = fontRubik),
    headlineMedium = TextStyle(fontFamily = fontRubik, fontWeight = FontWeight.W500),
    headlineLarge = TextStyle(fontFamily = fontRubik),
    displaySmall = TextStyle(fontFamily = fontRaleway, fontWeight = FontWeight.W600, fontSize = 30.sp ),
    displayMedium = TextStyle(fontFamily = fontRaleway, fontWeight = FontWeight.W600),
    displayLarge = TextStyle(fontFamily = fontRubik),
    bodyMedium = TextStyle(fontFamily = fontRubik),
    bodySmall = TextStyle(fontFamily = fontRubik),
    bodyLarge = TextStyle(fontFamily = fontRubik),
    labelLarge = TextStyle(fontFamily = fontRubik),
    labelMedium = TextStyle(fontFamily = fontRubik),
    labelSmall = TextStyle(fontFamily = fontRubik),
)

fun typographyFromDefaults(
    displayLarge: TextStyle,
    displayMedium: TextStyle,
    displaySmall: TextStyle,
    headlineLarge: TextStyle,
    headlineMedium: TextStyle,
    headlineSmall: TextStyle,
    titleLarge: TextStyle,
    titleMedium: TextStyle,
    titleSmall: TextStyle,
    bodyLarge: TextStyle,
    bodyMedium: TextStyle,
    bodySmall: TextStyle,
    labelLarge: TextStyle,
    labelMedium: TextStyle,
    labelSmall: TextStyle
): Typography {
    val defaults = Typography()
    return Typography(
        titleSmall = defaults.titleSmall.merge(titleSmall),
        titleMedium = defaults.titleMedium.merge(titleMedium),
        titleLarge = defaults.titleLarge.merge(titleLarge),
        headlineSmall = defaults.headlineSmall.merge(headlineSmall),
        headlineMedium = defaults.headlineMedium.merge(headlineMedium),
        headlineLarge = defaults.headlineLarge.merge(headlineLarge),
        displaySmall = defaults.displaySmall.merge(displaySmall),
        displayMedium = defaults.displayMedium.merge(displayMedium),
        displayLarge = defaults.displayLarge.merge(displayLarge),
        bodyMedium = defaults.bodyMedium.merge(bodyMedium),
        bodySmall = defaults.bodySmall.merge(bodySmall),
        bodyLarge = defaults.bodyLarge.merge(bodyLarge),
        labelLarge = defaults.labelLarge.merge(labelLarge),
        labelMedium = defaults.labelMedium.merge(labelMedium),
        labelSmall = defaults.labelSmall.merge(labelSmall),
    )
}

