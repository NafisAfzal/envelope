package com.abrarshakhi.envelope.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abrarshakhi.envelope.common.navigation.AppRoute
import com.abrarshakhi.envelope.common.navigation.AppRoute.AdminSetup
import com.abrarshakhi.envelope.common.navigation.AppRoute.ContactsSetup
import com.abrarshakhi.envelope.common.navigation.AppRoute.PermissionsSetup
import com.abrarshakhi.envelope.common.navigation.AppRoute.SelfDestructSetup
import com.abrarshakhi.envelope.common.navigation.AppRoute.UnlockPasswordSetup

/**
 * Navigation-skeleton placeholder screens. Copy is illustrative only and must
 * not be treated as final product language. No authentication, storage,
 * permissions, or backend calls happen here.
 */
@Composable
fun FlowScreen(
    title: String,
    body: String,
    primaryLabel: String,
    onPrimary: () -> Unit,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
    secondaryLabel: String? = null,
    onSecondary: (() -> Unit)? = null,
) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(text = title, style = MaterialTheme.typography.headlineSmall)
            Text(text = body, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 52.dp),
            ) {
                Text(text = primaryLabel)
            }
            if (secondaryLabel != null && onSecondary != null) {
                OutlinedButton(
                    onClick = onSecondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 52.dp),
                ) {
                    Text(text = secondaryLabel)
                }
            }
            if (onBack != null) {
                TextButton(
                    onClick = onBack,
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 48.dp),
                ) {
                    Text(text = "Back")
                }
            }
        }
    }
}

@Composable
fun RegisterScreen(
    onContinue: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowScreen(
        title = "Create account",
        body = "Registration fields will live here. Continuing moves to first-time setup.",
        primaryLabel = "Continue",
        onPrimary = onContinue,
        onBack = onBack,
        modifier = modifier,
    )
}

@Composable
fun SetupOverviewScreen(
    onOpenSetup: (AppRoute) -> Unit,
    onContinue: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(text = "Safety setup", style = MaterialTheme.typography.headlineSmall)
            Text(
                text = "Review each item, then continue to your dashboard. " +
                    "Completion is not saved yet.",
                style = MaterialTheme.typography.bodyLarge,
            )
            SetupItemButton(label = "Administrator setup", onClick = { onOpenSetup(AdminSetup) })
            SetupItemButton(label = "Emergency contacts", onClick = { onOpenSetup(ContactsSetup) })
            SetupItemButton(label = "Permissions", onClick = { onOpenSetup(PermissionsSetup) })
            SetupItemButton(
                label = "App unlock password",
                onClick = { onOpenSetup(UnlockPasswordSetup) },
            )
            SetupItemButton(
                label = "Self-destruct password",
                onClick = { onOpenSetup(SelfDestructSetup) },
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onContinue,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 52.dp),
            ) {
                Text(text = "Continue to dashboard")
            }
            TextButton(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp),
            ) {
                Text(text = "Back")
            }
        }
    }
}

@Composable
private fun SetupItemButton(label: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 52.dp),
    ) {
        Text(text = label)
    }
}

@Composable
private fun SetupStepScreen(
    title: String,
    body: String,
    onDone: () -> Unit,
    onBack: () -> Unit,
    onSkip: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowScreen(
        title = title,
        body = body,
        primaryLabel = "Done",
        onPrimary = onDone,
        secondaryLabel = "Skip",
        onSecondary = onSkip,
        onBack = onBack,
        modifier = modifier,
    )
}

@Composable
fun AdminSetupScreen(
    onDone: () -> Unit,
    onBack: () -> Unit,
    onSkip: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SetupStepScreen(
        title = "Administrator setup",
        body = "Identify the person whose safety plan this device holds. Details will be added later.",
        onDone = onDone,
        onBack = onBack,
        onSkip = onSkip,
        modifier = modifier,
    )
}

@Composable
fun ContactsSetupScreen(
    onDone: () -> Unit,
    onBack: () -> Unit,
    onSkip: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SetupStepScreen(
        title = "Emergency contacts",
        body = "Add the trusted people an emergency release can reach. Contact management will be added later.",
        onDone = onDone,
        onBack = onBack,
        onSkip = onSkip,
        modifier = modifier,
    )
}

@Composable
fun PermissionsSetupScreen(
    onDone: () -> Unit,
    onBack: () -> Unit,
    onSkip: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SetupStepScreen(
        title = "Permissions",
        body = "Review the device permissions Aegis SOS needs, and when each one is used.",
        onDone = onDone,
        onBack = onBack,
        onSkip = onSkip,
        modifier = modifier,
    )
}

@Composable
fun UnlockPasswordSetupScreen(
    onDone: () -> Unit,
    onBack: () -> Unit,
    onSkip: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SetupStepScreen(
        title = "App unlock password",
        body = "Choose the password that unlocks the app on this device. Entry fields will be added later.",
        onDone = onDone,
        onBack = onBack,
        onSkip = onSkip,
        modifier = modifier,
    )
}

@Composable
fun SelfDestructSetupScreen(
    onDone: () -> Unit,
    onBack: () -> Unit,
    onSkip: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SetupStepScreen(
        title = "Self-destruct password",
        body = "Choose the separate password reserved for emergency data removal. Entry fields will be added later.",
        onDone = onDone,
        onBack = onBack,
        onSkip = onSkip,
        modifier = modifier,
    )
}
