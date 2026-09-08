package com.abrarshakhi.envelope.common.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abrarshakhi.envelope.R
import com.abrarshakhi.envelope.common.ui.theme.aegisSurfaces

/**
 * Shared list-row language for readiness and settings items:
 * semantic icon in a soft tonal chip, strong left-aligned primary
 * label, muted secondary status, and a chevron affordance. One
 * visual system across screens.
 */
@Composable
fun SettingsListRow(
    label: String,
    supporting: String,
    iconRes: Int,
    onClick: () -> Unit,
    supportingColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    iconTint: Color = MaterialTheme.colorScheme.secondary,
    iconChipColor: Color = MaterialTheme.aegisSurfaces.elevated,
) {
    Surface(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 64.dp),
        shape = MaterialTheme.shapes.large,
        color = containerColor,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconChip(
                iconRes = iconRes,
                tint = iconTint,
                chipColor = iconChipColor,
            )
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = label,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 22.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = supporting,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 18.sp,
                    color = supportingColor,
                )
            }
            Icon(
                painter = painterResource(R.drawable.ic_chevron_right),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp),
            )
        }
    }
}

/**
 * Soft tonal chip that groups the row icon with its label. Uses the
 * elevated surface token so the chip reads as a deliberate layer,
 * not a decorative circle.
 */
@Composable
fun IconChip(
    iconRes: Int,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.secondary,
    chipColor: Color = MaterialTheme.aegisSurfaces.elevated,
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = chipColor,
        modifier = modifier.size(40.dp),
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(iconRes),
                contentDescription = null,
                modifier = Modifier.size(22.dp),
                colorFilter = ColorFilter.tint(tint),
            )
        }
    }
}
