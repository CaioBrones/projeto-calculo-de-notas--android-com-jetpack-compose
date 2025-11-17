package com.caio.gerenciador_de_notas.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val DarkBackgroundStart = Color(0xFF141414)
val DarkBackgroundEnd = Color(0xFF090909)
val PrimaryBlue = Color(0xFF0554F2)
val WhiteText = Color(0xFFFFFFFF)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    secondary = PrimaryBlue,
    tertiary = PrimaryBlue,
    background = DarkBackgroundStart,
    surface = Color.Transparent,
    onPrimary = WhiteText,
    onSecondary = WhiteText,
    onTertiary = WhiteText,
    onBackground = WhiteText,
    onSurface = WhiteText,
)

@Composable
fun GerenciadorNotasTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(DarkBackgroundStart, DarkBackgroundEnd)
                        )
                    )
            ) {
                content()
            }
        }
    )
}