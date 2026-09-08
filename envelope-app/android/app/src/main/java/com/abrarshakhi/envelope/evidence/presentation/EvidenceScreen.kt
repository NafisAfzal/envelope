package com.abrarshakhi.envelope.evidence.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abrarshakhi.envelope.R
import com.abrarshakhi.envelope.common.navigation.AppRoute
import com.abrarshakhi.envelope.common.ui.AppBottomNavigation
import com.abrarshakhi.envelope.common.ui.EmptyState

/**
 * S26 Evidence / Packages overview (first version). Honest empty
 * state: evidence capture and package management are not implemented
 * in this build, so this screen states that clearly instead of
 * showing fabricated packages.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvidenceScreen(
    onNavigate: (AppRoute) -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Evidence") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                ),
            )
        },
        bottomBar = {
            AppBottomNavigation(current = AppRoute.Evidence, onNavigate = onNavigate)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 24.dp, end = 24.dp, top = 4.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = "Encrypted evidence packages captured during an " +
                    "emergency are listed here.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            EmptyState(
                iconRes = R.drawable.ic_description,
                heading = "Your protected evidence will appear here.",
                explanation = "No evidence packages are available in this build yet.",
            )
        }
    }
}
