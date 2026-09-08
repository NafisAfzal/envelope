package com.abrarshakhi.envelope.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abrarshakhi.envelope.R
import com.abrarshakhi.envelope.common.navigation.AppRoute
import com.abrarshakhi.envelope.common.ui.AppBottomNavigation
import com.abrarshakhi.envelope.common.ui.SettingsListRow

/**
 * Safety setup / settings. Secondary destination that groups the
 * configuration concerns so Home stays focused on readiness.
 * Rows are grouped into Safety setup and Security sections.
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
            SettingsSectionLabel(text = "Safety setup")
            SettingsListRow(
                label = "Administrator",
                supporting = "Not configured",
                iconRes = R.drawable.ic_contact_phone,
                onClick = { onOpenSetup(AppRoute.AdminSetup) },
            )
            SettingsListRow(
                label = "Emergency contacts",
                supporting = "Not configured",
                iconRes = R.drawable.ic_group,
                onClick = { onOpenSetup(AppRoute.ContactsSetup) },
            )
            SettingsListRow(
                label = "Permissions",
                supporting = "Review the permissions the app requests",
                iconRes = R.drawable.ic_verified_user,
                onClick = { onOpenSetup(AppRoute.PermissionsSetup) },
            )
            SettingsSectionLabel(text = "Security")
            SettingsListRow(
                label = "App unlock password",
                supporting = "Not configured",
                iconRes = R.drawable.ic_settings,
                onClick = { onOpenSetup(AppRoute.UnlockPasswordSetup) },
            )
            SettingsListRow(
                label = "Self-destruct password",
                supporting = "Not configured",
                iconRes = R.drawable.ic_emergency,
                onClick = { onOpenSetup(AppRoute.SelfDestructSetup) },
            )
        }
    }
}

@Composable
private fun SettingsSectionLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 8.dp),
    )
}
