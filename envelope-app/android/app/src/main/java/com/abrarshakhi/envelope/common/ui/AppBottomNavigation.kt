package com.abrarshakhi.envelope.common.ui

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.abrarshakhi.envelope.common.navigation.AppRoute

/**
 * Shared bottom navigation for top-level destinations. SOS is an
 * action, not a destination, so it never appears here.
 */
@Composable
fun AppBottomNavigation(
    current: AppRoute,
    onNavigate: (AppRoute) -> Unit,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        NavigationBarItem(
            selected = current == AppRoute.Home,
            onClick = { if (current != AppRoute.Home) onNavigate(AppRoute.Home) },
            label = { Text(text = "Home") },
            icon = { AppNavDot(selected = current == AppRoute.Home) },
            colors = navColors(),
        )
        NavigationBarItem(
            selected = current == AppRoute.Evidence,
            onClick = { if (current != AppRoute.Evidence) onNavigate(AppRoute.Evidence) },
            label = { Text(text = "Evidence") },
            icon = { AppNavDot(selected = current == AppRoute.Evidence) },
            colors = navColors(),
        )
        NavigationBarItem(
            selected = current == AppRoute.Contacts,
            onClick = { if (current != AppRoute.Contacts) onNavigate(AppRoute.Contacts) },
            label = { Text(text = "Contacts") },
            icon = { AppNavDot(selected = current == AppRoute.Contacts) },
            colors = navColors(),
        )
        NavigationBarItem(
            selected = current == AppRoute.Settings,
            onClick = { if (current != AppRoute.Settings) onNavigate(AppRoute.Settings) },
            label = { Text(text = "Settings") },
            icon = { AppNavDot(selected = current == AppRoute.Settings) },
            colors = navColors(),
        )
    }
}

@Composable
private fun navColors() = NavigationBarItemDefaults.colors(
    selectedTextColor = MaterialTheme.colorScheme.primary,
    selectedIconColor = MaterialTheme.colorScheme.primary,
)

@Composable
private fun AppNavDot(selected: Boolean) {
    val color = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }
    Surface(
        shape = CircleShape,
        color = color,
        modifier = Modifier.size(24.dp),
        content = {},
    )
}
