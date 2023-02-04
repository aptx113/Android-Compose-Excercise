package com.danteyu.android_compose_exercise.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.danteyu.android_compose_exercise.R

// Set of Material typography styles to start with

val Cabin = FontFamily(
    Font(R.font.cabin_regular, FontWeight.Normal), Font(
        R.font.cabin_bold,
        FontWeight.Bold
    )
)

val HeroesTypography =
    Typography(
        displayLarge = TextStyle(
            fontFamily = Cabin,
            fontWeight = FontWeight.Normal,
            fontSize = 30.sp
        ),
        displayMedium = TextStyle(
            fontFamily = Cabin,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        ),
        displaySmall = TextStyle(
            fontFamily = Cabin,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        ),
        bodyLarge = TextStyle(fontFamily = Cabin, fontWeight = FontWeight.Normal, fontSize = 12.sp)
    )
val AbrilFatface = FontFamily(
    Font(R.font.abril_fatface_regular)
)

val Montserrat =
    FontFamily(
        Font(R.font.montserrat_regular),
        Font(R.font.montserrat_bold, FontWeight.Bold)
    )

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = AbrilFatface,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val UnscrambleTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

