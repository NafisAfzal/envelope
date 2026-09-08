package com.abrarshakhi.envelope.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            verticalArrangement = Arrangement.spacedBy(12.dp),
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
                route = AppRoute.AdminSetup,
                onOpenSetup = onOpenSetup,
            )
            SettingsItemRow(
                label = "Emergency contacts",
                supporting = "Not configured",
                route = AppRoute.ContactsSetup,
                onOpenSetup = onOpenSetup,
            )
            SettingsItemRow(
                label = "Permissions",
                supporting = "Review the permissions the app requests",
                route = AppRoute.PermissionsSetup,
                onOpenSetup = onOpenSetup,
            )
            SettingsItemRow(
                label = "App unlock password",
                supporting = "Not configured",
                route = AppRoute.UnlockPasswordSetup,
                onOpenSetup = onOpenSetup,
            )
            SettingsItemRow(
                label = "Self-destruct password",
                supporting = "Not configured",
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
    route: AppRoute,
    onOpenSetup: (AppRoute) -> Unit,
) {
    TextButton(
        onClick = { onOpenSetup(route) },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 64.dp),
        shape = MaterialTheme.shapes.large,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
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
            Text(
                text = "›",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
