package com.abrarshakhi.envelope.settings.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.abrarshakhi.envelope.R
import com.abrarshakhi.envelope.common.navigation.AppRoute
import com.abrarshakhi.envelope.common.ui.AppBottomNavigation

/**
 * Safety setup / settings. Secondary destination that groups the
 * configuration concerns (administration, contacts, permissions,
 * app unlock, self-destruct) so Home stays focused on readiness.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onOpenSetup: (AppRoute) -> Unit = {},
    onNavigate: (AppRoute) -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Safety setup") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
            )
        },
        bottomBar = {
            AppBottomNavigation(current = AppRoute.Settings, onNavigate = onNavigate)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = "Each step opens its setup screen. Completion is not " +
                    "saved yet.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            SettingsItemRow(
                label = "Administrator",
                supporting = "Not configured",
                iconRes = R.drawable.ic_contact_phone,
                route = AppRoute.AdminSetup,
                onOpenSetup = onOpenSetup,
            )
            SettingsItemRow(
                label = "Emergency contacts",
                supporting = "Not configured",
                iconRes = R.drawable.ic_group,
                route = AppRoute.ContactsSetup,
                onOpenSetup = onOpenSetup,
            )
            SettingsItemRow(
                label = "Permissions",
                supporting = "Review the permissions the app requests",
                iconRes = R.drawable.ic_verified_user,
                route = AppRoute.PermissionsSetup,
                onOpenSetup = onOpenSetup,
            )
            SettingsItemRow(
                label = "App unlock password",
                supporting = "Not configured",
                iconRes = R.drawable.ic_settings,
                route = AppRoute.UnlockPasswordSetup,
                onOpenSetup = onOpenSetup,
            )
            SettingsItemRow(
                label = "Self-destruct password",
                supporting = "Not configured",
                iconRes = R.drawable.ic_emergency,
                route = AppRoute.SelfDestructSetup,
                onOpenSetup = onOpenSetup,
            )
        }
    }
}

@Composable
private fun SettingsItemRow(
    label: String,
    supporting: String,
    iconRes: Int,
    route: AppRoute,
    onOpenSetup: (AppRoute) -> Unit,
) {
    Surface(
        onClick = { onOpenSetup(route) },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 72.dp),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(iconRes),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = supporting,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Icon(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp),
            )
        }
    }
}
