package com.abrarshakhi.envelope.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.abrarshakhi.envelope.R
import com.abrarshakhi.envelope.common.navigation.AppRoute
import com.abrarshakhi.envelope.common.ui.AppBottomNavigation
import com.abrarshakhi.envelope.common.ui.SettingsListRow
import com.abrarshakhi.envelope.common.ui.theme.aegisSurfaces

/**
 * Safety setup / settings — a security control center. Configuration
 * is grouped into Safety setup and Security sections, each a tonal
 * container, so Home stays focused on readiness and this screen
 * clearly reads as "where I configure the app".
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
            TopAppBar(
                title = { Text(text = "Safety setup") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
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
                .padding(start = 24.dp, end = 24.dp, top = 4.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            SettingsSectionLabel(text = "Safety setup")
            SettingsGroup {
                SettingsListRow(
                    label = "Administration",
                    supporting = "Not configured",
                    iconRes = R.drawable.ic_contact_phone,
                    onClick = { onOpenSetup(AppRoute.AdminSetup) },
                    containerColor = Color.Transparent,
                )
                SettingsListRow(
                    label = "Emergency contacts",
                    supporting = "Not configured",
                    iconRes = R.drawable.ic_group,
                    onClick = { onOpenSetup(AppRoute.ContactsSetup) },
                    containerColor = Color.Transparent,
                )
                SettingsListRow(
                    label = "Permissions",
                    supporting = "Review the permissions the app requests",
                    iconRes = R.drawable.ic_verified_user,
                    onClick = { onOpenSetup(AppRoute.PermissionsSetup) },
                    containerColor = Color.Transparent,
                )
            }
            SettingsSectionLabel(text = "Security")
            SettingsGroup {
                SettingsListRow(
                    label = "App unlock password",
                    supporting = "Not configured",
                    iconRes = R.drawable.ic_settings,
                    onClick = { onOpenSetup(AppRoute.UnlockPasswordSetup) },
                    containerColor = Color.Transparent,
                )
                SettingsListRow(
                    label = "Self-destruct password",
                    supporting = "Not configured",
                    iconRes = R.drawable.ic_emergency,
                    onClick = { onOpenSetup(AppRoute.SelfDestructSetup) },
                    containerColor = Color.Transparent,
                )
            }
        }
    }
}

@Composable
private fun SettingsSectionLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(start = 4.dp, top = 6.dp),
    )
}

@Composable
private fun SettingsGroup(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.aegisSurfaces.muted,
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            content()
        }
    }
}
