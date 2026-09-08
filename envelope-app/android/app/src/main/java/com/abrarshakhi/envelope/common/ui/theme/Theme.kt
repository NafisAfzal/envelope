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
 * Extended Aegis surface tokens that Material 3 has no slot for.
 * Components read these for tonal layering (elevated/muted surfaces)
 * so screens gain depth without extra colors per screen.
 */
data class AegisSurfaces(
    val elevated: androidx.compose.ui.graphics.Color,
    val muted: androidx.compose.ui.graphics.Color,
    val emergencyDeep: androidx.compose.ui.graphics.Color,
)

val LocalAegisSurfaces = staticCompositionLocalOf {
    AegisSurfaces(
        elevated = SurfaceElevatedDark,
        muted = SurfaceMutedDark,
        emergencyDeep = EmergencyDeepDark,
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
    emergencyDeep = EmergencyDeepLight,
)

private val DarkAegisSurfaces = AegisSurfaces(
    elevated = SurfaceElevatedDark,
    muted = SurfaceMutedDark,
    emergencyDeep = EmergencyDeepDark,
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
