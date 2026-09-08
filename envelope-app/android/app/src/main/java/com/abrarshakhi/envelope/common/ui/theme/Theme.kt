package com.abrarshakhi.envelope.common.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext

/**
 * Extended Aegis tokens that Material 3 has no slot for: tonal
 * surface layers for depth, warm text tones for hierarchy, and the
 * layered emergency (SOS) family. Screens compose depth from these
 * instead of flat single colors.
 */
data class AegisSurfaces(
    val elevated: androidx.compose.ui.graphics.Color,
    val muted: androidx.compose.ui.graphics.Color,
    val emergencyCrimson: androidx.compose.ui.graphics.Color,
    val emergencyBurgundy: androidx.compose.ui.graphics.Color,
    val emergencyCoral: androidx.compose.ui.graphics.Color,
    val emergencyOnCoral: androidx.compose.ui.graphics.Color,
    val emergencyRose: androidx.compose.ui.graphics.Color,
    val emergencyIvory: androidx.compose.ui.graphics.Color,
    val textPrimary: androidx.compose.ui.graphics.Color,
    val textSecondary: androidx.compose.ui.graphics.Color,
    val textTertiary: androidx.compose.ui.graphics.Color,
)

val LocalAegisSurfaces = staticCompositionLocalOf {
    AegisSurfaces(
        elevated = SurfaceElevatedDark,
        muted = SurfaceMutedDark,
        emergencyCrimson = EmergencyCrimsonDark,
        emergencyBurgundy = EmergencyBurgundyDark,
        emergencyCoral = EmergencyCoralDark,
        emergencyOnCoral = EmergencyOnCoralDark,
        emergencyRose = EmergencyRoseDark,
        emergencyIvory = EmergencyIvoryDark,
        textPrimary = OnMineralSurfaceDark,
        textSecondary = TextSecondaryDark,
        textTertiary = TextTertiaryDark,
    )
}

val MaterialTheme.aegisSurfaces: AegisSurfaces
    @Composable get() = LocalAegisSurfaces.current

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = PrimaryContainerLight,
    onPrimaryContainer = OnPrimaryContainerLight,
    secondary = SecondaryLight,
    onSecondary = OnSecondaryLight,
    secondaryContainer = SecondaryContainerLight,
    onSecondaryContainer = OnSecondaryContainerLight,
    tertiary = TertiaryLight,
    onTertiary = OnTertiaryLight,
    tertiaryContainer = TertiaryContainerLight,
    onTertiaryContainer = OnTertiaryContainerLight,
    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorContainerLight,
    onErrorContainer = OnErrorContainerLight,
    background = MineralSurfaceLight,
    onBackground = OnMineralSurfaceLight,
    surface = MineralSurfaceLight,
    onSurface = OnMineralSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
    outline = OutlineLight,
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimaryDark,
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainerDark,
    secondary = SecondaryDark,
    onSecondary = OnSecondaryDark,
    secondaryContainer = SecondaryContainerDark,
    onSecondaryContainer = OnSecondaryContainerDark,
    tertiary = TertiaryDark,
    onTertiary = OnTertiaryDark,
    tertiaryContainer = TertiaryContainerDark,
    onTertiaryContainer = OnTertiaryContainerDark,
    error = ErrorDark,
    onError = OnErrorDark,
    errorContainer = ErrorContainerDark,
    onErrorContainer = OnErrorContainerDark,
    background = MineralSurfaceDark,
    onBackground = OnMineralSurfaceDark,
    surface = MineralSurfaceDark,
    onSurface = OnMineralSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    outline = OutlineDark,
)

private val LightAegisSurfaces = AegisSurfaces(
    elevated = SurfaceElevatedLight,
    muted = SurfaceMutedLight,
    emergencyCrimson = EmergencyCrimsonLight,
    emergencyBurgundy = EmergencyBurgundyLight,
    emergencyCoral = EmergencyCoralLight,
    emergencyOnCoral = EmergencyOnCoralLight,
    emergencyRose = EmergencyRoseLight,
    emergencyIvory = EmergencyIvoryLight,
    textPrimary = OnMineralSurfaceLight,
    textSecondary = TextSecondaryLight,
    textTertiary = TextTertiaryLight,
)

private val DarkAegisSurfaces = AegisSurfaces(
    elevated = SurfaceElevatedDark,
    muted = SurfaceMutedDark,
    emergencyCrimson = EmergencyCrimsonDark,
    emergencyBurgundy = EmergencyBurgundyDark,
    emergencyCoral = EmergencyCoralDark,
    emergencyOnCoral = EmergencyOnCoralDark,
    emergencyRose = EmergencyRoseDark,
    emergencyIvory = EmergencyIvoryDark,
    textPrimary = OnMineralSurfaceDark,
    textSecondary = TextSecondaryDark,
    textTertiary = TextTertiaryDark,
)

@Composable
fun EnvelopeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Aegis SOS uses its own identity palette; dynamic color stays
    // opt-in so the teal readiness language is not wallpaper-tinted.
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    androidx.compose.runtime.CompositionLocalProvider(
        LocalAegisSurfaces provides if (darkTheme) DarkAegisSurfaces else LightAegisSurfaces,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
