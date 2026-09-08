package com.abrarshakhi.envelope.common.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoute {
    @Serializable
    data object Onboarding : AppRoute

    @Serializable
    data object Login : AppRoute

    @Serializable
    data object Register : AppRoute

    @Serializable
    data object SetupOverview : AppRoute

    @Serializable
    data object AdminSetup : AppRoute

    @Serializable
    data object ContactsSetup : AppRoute

    @Serializable
    data object PermissionsSetup : AppRoute

    @Serializable
    data object UnlockPasswordSetup : AppRoute

    @Serializable
    data object SelfDestructSetup : AppRoute

    @Serializable
    data object Home : AppRoute

    @Serializable
    data object Evidence : AppRoute

    @Serializable
    data object Contacts : AppRoute

    @Serializable
    data object Settings : AppRoute
}
