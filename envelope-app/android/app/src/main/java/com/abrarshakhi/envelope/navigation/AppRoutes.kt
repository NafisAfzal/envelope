package com.abrarshakhi.envelope.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Type-safe Navigation 3 destinations for the Aegis SOS onboarding skeleton.
 *
 * Placeholders only: labels follow the UX architecture screen inventory
 * (Welcome ~ S02, Login/Register ~ S05 variants, Setup Overview ~ S04
 * checklist, setup items ~ S22/S35/S34/S08 families, Dashboard ~ S10).
 * No authentication, storage, or backend integration happens here.
 */
sealed interface AppRoute : NavKey

@Serializable
data object Welcome : AppRoute

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
data object Dashboard : AppRoute
