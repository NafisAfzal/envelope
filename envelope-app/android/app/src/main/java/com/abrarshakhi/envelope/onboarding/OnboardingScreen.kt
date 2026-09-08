package com.abrarshakhi.envelope.onboarding

import androidx.compose.runtime.Composable

/**
 * Onboarding entry screen: welcomes the user and offers sign-in or account
 * creation. Placeholder content only — no authentication, storage, or
 * backend calls happen here.
 */
@Composable
fun OnboardingScreen(onFinish: () -> Unit, onRegister: () -> Unit) {
    FlowScreen(
        title = "Welcome to Aegis SOS",
        body = "Aegis SOS keeps your emergency evidence encrypted on this device. " +
            "Sign in or create an account to set up your safety plan.",
        primaryLabel = "Create account",
        onPrimary = onRegister,
        secondaryLabel = "Sign in",
        onSecondary = onFinish,
    )
}
