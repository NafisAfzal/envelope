package com.abrarshakhi.envelope.common.di

import com.abrarshakhi.envelope.auth.presentation.LoginScreen
import com.abrarshakhi.envelope.common.navigation.AppNavigator
import com.abrarshakhi.envelope.common.navigation.AppRoute
import com.abrarshakhi.envelope.common.navigation.AppRoute.AdminSetup
import com.abrarshakhi.envelope.common.navigation.AppRoute.ContactsSetup
import com.abrarshakhi.envelope.common.navigation.AppRoute.PermissionsSetup
import com.abrarshakhi.envelope.common.navigation.AppRoute.SelfDestructSetup
import com.abrarshakhi.envelope.common.navigation.AppRoute.UnlockPasswordSetup
import com.abrarshakhi.envelope.contacts.presentation.ContactsScreen
import com.abrarshakhi.envelope.evidence.presentation.EvidenceScreen
import com.abrarshakhi.envelope.home.presentation.HomeScreen
import com.abrarshakhi.envelope.settings.presentation.SettingsScreen
import com.abrarshakhi.envelope.onboarding.AdminSetupScreen
import com.abrarshakhi.envelope.onboarding.ContactsSetupScreen
import com.abrarshakhi.envelope.onboarding.OnboardingScreen
import com.abrarshakhi.envelope.onboarding.PermissionsSetupScreen
import com.abrarshakhi.envelope.onboarding.RegisterScreen
import com.abrarshakhi.envelope.onboarding.SelfDestructSetupScreen
import com.abrarshakhi.envelope.onboarding.SetupOverviewScreen
import com.abrarshakhi.envelope.onboarding.UnlockPasswordSetupScreen
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val navigationModule = module {
    single { AppNavigator(startDestination = AppRoute.Onboarding) }

    navigation<AppRoute.Onboarding> {
        val navigator = get<AppNavigator>()
        OnboardingScreen(
            onFinish = { navigator.navigateTo(AppRoute.Login) },
            onRegister = { navigator.navigateTo(AppRoute.Register) },
        )
    }

    navigation<AppRoute.Login> {
        val navigator = get<AppNavigator>()
        LoginScreen(
            onLoginSuccess = { navigator.replaceAll(AppRoute.Home) },
        )
    }

    navigation<AppRoute.Register> {
        val navigator = get<AppNavigator>()
        RegisterScreen(
            onContinue = { navigator.navigateTo(AppRoute.SetupOverview) },
            onBack = { navigator.goBack() },
        )
    }

    navigation<AppRoute.SetupOverview> {
        val navigator = get<AppNavigator>()
        SetupOverviewScreen(
            onOpenSetup = { navigator.navigateTo(it) },
            onContinue = { navigator.replaceAll(AppRoute.Home) },
            onBack = { navigator.goBack() },
        )
    }

    navigation<AdminSetup> {
        val navigator = get<AppNavigator>()
        AdminSetupScreen(
            onDone = { navigator.goBack() },
            onBack = { navigator.goBack() },
            onSkip = { navigator.goBack() },
        )
    }

    navigation<ContactsSetup> {
        val navigator = get<AppNavigator>()
        ContactsSetupScreen(
            onDone = { navigator.goBack() },
            onBack = { navigator.goBack() },
            onSkip = { navigator.goBack() },
        )
    }

    navigation<PermissionsSetup> {
        val navigator = get<AppNavigator>()
        PermissionsSetupScreen(
            onDone = { navigator.goBack() },
            onBack = { navigator.goBack() },
            onSkip = { navigator.goBack() },
        )
    }

    navigation<UnlockPasswordSetup> {
        val navigator = get<AppNavigator>()
        UnlockPasswordSetupScreen(
            onDone = { navigator.goBack() },
            onBack = { navigator.goBack() },
            onSkip = { navigator.goBack() },
        )
    }

    navigation<SelfDestructSetup> {
        val navigator = get<AppNavigator>()
        SelfDestructSetupScreen(
            onDone = { navigator.goBack() },
            onBack = { navigator.goBack() },
            onSkip = { navigator.goBack() },
        )
    }

    navigation<AppRoute.Home> {
        val navigator = get<AppNavigator>()
        HomeScreen(
            onNavigate = { navigator.navigateTo(it) },
        )
    }

    navigation<AppRoute.Evidence> {
        val navigator = get<AppNavigator>()
        EvidenceScreen(
            onNavigate = { navigator.navigateTo(it) },
        )
    }

    navigation<AppRoute.Contacts> {
        val navigator = get<AppNavigator>()
        ContactsScreen(
            onNavigate = { navigator.navigateTo(it) },
        )
    }

    navigation<AppRoute.Settings> {
        val navigator = get<AppNavigator>()
        SettingsScreen(
            onOpenSetup = { navigator.navigateTo(it) },
            onNavigate = { navigator.navigateTo(it) },
        )
    }
}
