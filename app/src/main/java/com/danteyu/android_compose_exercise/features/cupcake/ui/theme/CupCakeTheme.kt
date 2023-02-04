package com.danteyu.android_compose_exercise.features.cupcake.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val CupCakeDarkColorPalette =
    darkColorScheme(
        primary = Pink400,
        onPrimary = Black,
        secondary = Pink950,
        onSecondary = White,
        tertiary = Purple400,
        onTertiary = Black
    )

private val CupCakeLightColorPalette = lightColorScheme(
    primary = Pink600,
    onPrimary = White,
    secondary = Pink950,
    onSecondary = White,
    tertiary = Purple400,
    onTertiary = Black
)

@Composable
fun CupCakeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        CupCakeDarkColorPalette
    } else {
        CupCakeLightColorPalette
    }
    MaterialTheme(
        colorScheme = colors,
        typography = CupCakeTypography,
        shapes = CupCakeShapes,
        content = content
    )
}