package org.hotaku.listy.core.presentation.composables

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.hotaku.listy.core.presentation.brightGreen
import org.hotaku.listy.core.presentation.powder

@Composable
fun SolidButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = brightGreen,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors().copy(
            disabledContainerColor = color.copy(alpha = .5f),
            containerColor = color,
            contentColor = powder
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}