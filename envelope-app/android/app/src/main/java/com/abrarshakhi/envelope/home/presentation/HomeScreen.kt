package com.abrarshakhi.envelope.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abrarshakhi.envelope.R
import com.abrarshakhi.envelope.common.navigation.AppRoute
import com.abrarshakhi.envelope.common.ui.AppBottomNavigation
import com.abrarshakhi.envelope.common.ui.IconChip
import com.abrarshakhi.envelope.common.ui.SettingsListRow
import com.abrarshakhi.envelope.common.ui.theme.AccentPlumDark
import com.abrarshakhi.envelope.common.ui.theme.AccentPlumLight
import com.abrarshakhi.envelope.common.ui.theme.aegisSurfaces

/**
 * S10 Home / Ready. Calm everyday home: prominent Aegis SOS brand
 * hero, the Dead Man's Switch as the primary preparedness module, a
 * grouped readiness list, and a rich layered emergency action. All
 * configuration lives behind Settings. Nothing here pretends to be a
 * live backend capability.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigate: (AppRoute) -> Unit = {},
) {
    val s = MaterialTheme.aegisSurfaces
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Emergency readiness") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = s.textTertiary,
                ),
            )
        },
        bottomBar = {
            AppBottomNavigation(current = AppRoute.Home, onNavigate = onNavigate)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(start = 24.dp, end = 24.dp, top = 4.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            HomeHeading()
            DmsModule()
            ReadinessSummary(onNavigate = onNavigate)
            SosAction()
        }
    }
}

@Composable
private fun HomeHeading() {
    val s = MaterialTheme.aegisSurfaces
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(
            text = "Aegis SOS",
            style = MaterialTheme.typography.headlineMedium,
            color = if (isSystemInDarkTheme()) AccentPlumDark else AccentPlumLight,
        )
        Text(
            text = "Ready when you need it.",
            style = MaterialTheme.typography.titleMedium,
            color = s.textPrimary,
            modifier = Modifier.padding(top = 2.dp),
        )
        Text(
            text = "Your emergency plan at a glance.",
            style = MaterialTheme.typography.bodyMedium,
            color = s.textSecondary,
        )
    }
}

@Composable
private fun DmsModule() {
    // Subtle indigo → sea-glass depth wash; nearly imperceptible,
    // creating surface depth rather than decoration.
    val gradient = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.tertiaryContainer,
            MaterialTheme.colorScheme.secondaryContainer,
        ),
    )
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Color.Transparent,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradient, RoundedCornerShape(24.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconChip(
                    iconRes = R.drawable.ic_schedule,
                    tint = MaterialTheme.colorScheme.tertiary,
                    chipColor = Color.Transparent,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Dead Man's Switch",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                    )
                    Text(
                        text = "Not configured — check-in scheduling arrives in a " +
                            "future update.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.75f),
                    )
                }
            }
        }
    }
}

@Composable
private fun ReadinessSummary(onNavigate: (AppRoute) -> Unit) {
    val s = MaterialTheme.aegisSurfaces
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Readiness",
                style = MaterialTheme.typography.titleMedium,
                color = s.textPrimary,
                modifier = Modifier.weight(1f),
            )
            TextButton(
                onClick = { onNavigate(AppRoute.Settings) },
            ) {
                Text(text = "Review setup")
            }
        }
        SettingsListRow(
            label = "Contacts",
            supporting = "Not configured",
            iconRes = R.drawable.ic_group,
            onClick = { onNavigate(AppRoute.ContactsSetup) },
            supportingColor = statusColor(StatusTone.ATTENTION),
        )
        SettingsListRow(
            label = "Evidence",
            supporting = "Not configured",
            iconRes = R.drawable.ic_description,
            onClick = { onNavigate(AppRoute.Evidence) },
            supportingColor = statusColor(StatusTone.ATTENTION),
        )
        SettingsListRow(
            label = "Permissions",
            supporting = "Review in setup",
            iconRes = R.drawable.ic_verified_user,
            onClick = { onNavigate(AppRoute.PermissionsSetup) },
            supportingColor = statusColor(StatusTone.ATTENTION),
        )
    }
}

enum class StatusTone { ATTENTION, READY, INFO }

@Composable
internal fun statusColor(tone: StatusTone) = when (tone) {
    StatusTone.ATTENTION -> com.abrarshakhi.envelope.common.ui.theme.AttentionDark
    StatusTone.READY -> com.abrarshakhi.envelope.common.ui.theme.SuccessDark
    StatusTone.INFO -> com.abrarshakhi.envelope.common.ui.theme.InfoDark
}

@Composable
private fun SosAction() {
    val s = MaterialTheme.aegisSurfaces
    // Layered emergency depth: deep crimson base flowing into rich
    // burgundy. The coral CTA is the brightest element inside the
    // region; text is warm ivory, never pure white.
    val gradient = Brush.linearGradient(
        colors = listOf(s.emergencyCrimson, s.emergencyBurgundy),
    )
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        color = Color.Transparent,
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = s.emergencyRose.copy(alpha = 0.25f),
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradient, RoundedCornerShape(28.dp))
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 18.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.ic_emergency),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        colorFilter = ColorFilter.tint(s.emergencyRose),
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "EMERGENCY",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 2.sp,
                        color = s.emergencyRose,
                    )
                }
                Text(
                    text = "If you are in danger, starting SOS begins " +
                        "emergency activation.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = s.emergencyIvory.copy(alpha = 0.9f),
                )
                Button(
                    onClick = {},
                    enabled = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 60.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = s.emergencyCoral,
                        contentColor = s.emergencyOnCoral,
                        disabledContainerColor = s.emergencyCoral.copy(alpha = 0.75f),
                        disabledContentColor = s.emergencyOnCoral,
                    ),
                ) {
                    Text(
                        text = "Start SOS",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Text(
                    text = "Dispatch is not implemented in this build — " +
                        "nothing is sent.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = s.emergencyIvory.copy(alpha = 0.6f),
                )
            }
        }
    }
}
