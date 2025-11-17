package com.caio.gerenciador_de_notas.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.caio.gerenciador_de_notas.R

val LemonMilk = FontFamily(
    Font(R.font.lemon_milk, FontWeight.Normal)
)

val Urbanist = FontFamily(
    Font(R.font.urbanist, FontWeight.Normal)
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = LemonMilk,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        color = PrimaryBlue
    ),
    headlineMedium = TextStyle(
        fontFamily = LemonMilk,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        color = PrimaryBlue
    ),
    headlineSmall = TextStyle(
        fontFamily = LemonMilk,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        color = PrimaryBlue
    ),
    titleLarge = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        color = WhiteText
    ),
    titleMedium = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = WhiteText
    ),
    titleSmall = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = WhiteText
    ),
    bodyLarge = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = WhiteText
    ),
    bodyMedium = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = WhiteText
    ),
    bodySmall = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = WhiteText
    ),
    labelLarge = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = WhiteText
    ),
    labelMedium = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = WhiteText
    ),
    labelSmall = TextStyle(
        fontFamily = Urbanist,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        color = WhiteText
    )
)