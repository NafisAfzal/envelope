package com.abrarshakhi.envelope.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abrarshakhi.envelope.common.ui.theme.aegisSurfaces

/**
 * Shared empty-state pattern: semantic icon in a soft tonal
 * container, short heading, one concise explanation. Used by
 * Evidence and Contacts so secondary pages belong to the same
 * design system and communicate purpose, not "no data".
 */
@Composable
fun EmptyState(
    iconRes: Int,
    heading: String,
    explanation: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.tertiaryContainer,
                modifier = Modifier.size(72.dp),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(36.dp),
                        colorFilter = ColorFilter.tint(
                            MaterialTheme.colorScheme.onTertiaryContainer,
                        ),
                    )
                }
            }
            Text(
                text = heading,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 24.sp,
                color = MaterialTheme.aegisSurfaces.textPrimary,
            )
            Text(
                text = explanation,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = MaterialTheme.aegisSurfaces.textSecondary,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
