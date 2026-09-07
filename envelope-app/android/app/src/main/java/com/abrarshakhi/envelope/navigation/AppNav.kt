package com.abrarshakhi.envelope.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.abrarshakhi.envelope.ui.onboarding.AdminSetupScreen
import com.abrarshakhi.envelope.ui.onboarding.ContactsSetupScreen
import com.abrarshakhi.envelope.ui.onboarding.DashboardScreen
import com.abrarshakhi.envelope.ui.onboarding.LoginScreen
import com.abrarshakhi.envelope.ui.onboarding.PermissionsSetupScreen
import com.abrarshakhi.envelope.ui.onboarding.RegisterScreen
import com.abrarshakhi.envelope.ui.onboarding.SelfDestructSetupScreen
import com.abrarshakhi.envelope.ui.onboarding.SetupOverviewScreen
import com.abrarshakhi.envelope.ui.onboarding.UnlockPasswordSetupScreen
import com.abrarshakhi.envelope.ui.onboarding.WelcomeScreen

@Composable
fun EnvelopeApp(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(Welcome)
    NavDisplay(
        backStack = backStack,
        modifier = modifier.fillMaxSize(),
        onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Welcome -> NavEntry(key) {
                    WelcomeScreen(
                        onLogin = { backStack.add(Login) },
                        onRegister = { backStack.add(Register) },
                    )
                }
                is Login -> NavEntry(key) {
                    LoginScreen(
                        onContinue = { backStack.add(SetupOverview) },
                        onBack = { backStack.removeLastOrNull() },
                    )
                }
                is Register -> NavEntry(key) {
                    RegisterScreen(
                        onContinue = { backStack.add(SetupOverview) },
                        onBack = { backStack.removeLastOrNull() },
                    )
                }
                is SetupOverview -> NavEntry(key) {
                    SetupOverviewScreen(
                        onOpenSetup = { backStack.add(it) },
                        onContinue = { backStack.add(Dashboard) },
                        onBack = { backStack.removeLastOrNull() },
                    )
                }
                is AdminSetup -> NavEntry(key) {
                    AdminSetupScreen(
                        onDone = { backStack.removeLastOrNull() },
                        onBack = { backStack.removeLastOrNull() },
                        onSkip = { backStack.removeLastOrNull() },
                    )
                }
                is ContactsSetup -> NavEntry(key) {
                    ContactsSetupScreen(
                        onDone = { backStack.removeLastOrNull() },
                        onBack = { backStack.removeLastOrNull() },
                        onSkip = { backStack.removeLastOrNull() },
                    )
                }
                is PermissionsSetup -> NavEntry(key) {
                    PermissionsSetupScreen(
                        onDone = { backStack.removeLastOrNull() },
                        onBack = { backStack.removeLastOrNull() },
                        onSkip = { backStack.removeLastOrNull() },
                    )
                }
                is UnlockPasswordSetup -> NavEntry(key) {
                    UnlockPasswordSetupScreen(
                        onDone = { backStack.removeLastOrNull() },
                        onBack = { backStack.removeLastOrNull() },
                        onSkip = { backStack.removeLastOrNull() },
                    )
                }
                is SelfDestructSetup -> NavEntry(key) {
                    SelfDestructSetupScreen(
                        onDone = { backStack.removeLastOrNull() },
                        onBack = { backStack.removeLastOrNull() },
                        onSkip = { backStack.removeLastOrNull() },
                    )
                }
                is Dashboard -> NavEntry(key) {
                    DashboardScreen(onBack = { backStack.removeLastOrNull() })
                }
                else -> error("Unknown destination: $key")
            }
        },
    )
}
