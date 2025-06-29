package com.ozman.weatherapptask.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Light Theme Colors
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val DetailBackgroundLight = Color(0xFFF0F4F8)
val TextPrimaryLight = Color(0xFF222222)
val TextSecondaryLight = Color(0xFF555555)
val TextAccentLight = Color(0xFF777777)

// Dark Theme Colors
val DetailBackgroundDark = Color(0xFF1C1C1E)
val TextPrimaryDark = Color(0xFFEDEDED)
val TextSecondaryDark = Color(0xFFCCCCCC)
val TextAccentDark = Color(0xFF999999)
// ColorPalette.kt

// Light Mode
val LightDetailBackground = Color(0xFFF0F4F8)
val LightTextPrimary = Color(0xFF222222)
val LightTextSecondary = Color(0xFF555555)
val LightTextAccent = Color(0xFF777777)
val LightCardBackground = Color(0xFFFFFFFF)

// Dark Mode
val DarkDetailBackground = Color(0xFF1C1C1E)
val DarkTextPrimary = Color(0xFFEDEDED)
val DarkTextSecondary = Color(0xFFCCCCCC)
val DarkTextAccent = Color(0xFF999999)
val DarkCardBackground = Color(0xFF2C2C2E)
// ColorPalette.kt

object AppThemeColors {
    val detailBackground: Color
        @Composable get() = if (isSystemInDarkTheme()) DetailBackgroundDark else DetailBackgroundLight

    val textPrimary: Color
        @Composable get() = if (isSystemInDarkTheme()) TextPrimaryDark else TextPrimaryLight

    val textSecondary: Color
        @Composable get() = if (isSystemInDarkTheme()) TextSecondaryDark else TextSecondaryLight

    val textAccent: Color
        @Composable get() = if (isSystemInDarkTheme()) TextAccentDark else TextAccentLight
}
