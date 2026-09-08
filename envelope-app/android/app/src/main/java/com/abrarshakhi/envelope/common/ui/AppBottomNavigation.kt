package com.abrarshakhi.envelope.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.abrarshakhi.envelope.R
import com.abrarshakhi.envelope.common.navigation.AppRoute

/**
 * Shared bottom navigation for top-level destinations, using the
 * Material Symbols outlined family. SOS is an action, not a
 * destination, so it never appears here.
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
            icon = {
                NavIcon(
                    iconRes = R.drawable.ic_home,
                    selected = current == AppRoute.Home,
                )
            },
            colors = navColors(),
        )
        NavigationBarItem(
            selected = current == AppRoute.Evidence,
            onClick = { if (current != AppRoute.Evidence) onNavigate(AppRoute.Evidence) },
            label = { Text(text = "Evidence") },
            icon = {
                NavIcon(
                    iconRes = R.drawable.ic_folder,
                    selected = current == AppRoute.Evidence,
                )
            },
            colors = navColors(),
        )
        NavigationBarItem(
            selected = current == AppRoute.Contacts,
            onClick = { if (current != AppRoute.Contacts) onNavigate(AppRoute.Contacts) },
            label = { Text(text = "Contacts") },
            icon = {
                NavIcon(
                    iconRes = R.drawable.ic_group,
                    selected = current == AppRoute.Contacts,
                )
            },
            colors = navColors(),
        )
        NavigationBarItem(
            selected = current == AppRoute.Settings,
            onClick = { if (current != AppRoute.Settings) onNavigate(AppRoute.Settings) },
            label = { Text(text = "Settings") },
            icon = {
                NavIcon(
                    iconRes = R.drawable.ic_settings,
                    selected = current == AppRoute.Settings,
                )
            },
            colors = navColors(),
        )
    }
}

@Composable
private fun navColors() = NavigationBarItemDefaults.colors(
    selectedTextColor = MaterialTheme.colorScheme.primary,
    selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
    indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
)

@Composable
private fun NavIcon(iconRes: Int, selected: Boolean) {
    val tint = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }
    Image(
        painter = painterResource(iconRes),
        contentDescription = null,
        modifier = Modifier.size(24.dp),
        colorFilter = ColorFilter.tint(tint),
    )
}
