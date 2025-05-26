package org.hotaku.listy.core.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.hotaku.listy.core.presentation.primaryBlue
import org.hotaku.listy.core.presentation.background
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SolidButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = primaryBlue,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors().copy(
            disabledContainerColor = color.copy(alpha = .5f),
            containerColor = color,
            contentColor = background
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview
@Composable
fun SolidButtonPreview() {
    SolidButton(
        text = "Hello",
        onClick = {}
    )
}

@Composable
fun SmallSolidButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = primaryBlue,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors().copy(
            disabledContainerColor = color.copy(alpha = .5f),
            containerColor = color,
            contentColor = background
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview
@Composable
fun SmallSolidButtonPreview() {
    SmallSolidButton(
        text = "Hello",
        onClick = {}
    )
}

@Composable
fun SecondarySolidButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = primaryBlue,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.filledTonalButtonColors().copy(
            disabledContainerColor = color.copy(alpha = .05f),
            containerColor =  color.copy(alpha = .2f),
            contentColor = color
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview
@Composable
fun SecondarySolidButtonPreview() {
    Surface(
        color = background
    ) {
        SecondarySolidButton(
            text = "Hello",
            onClick = {}
        )
    }
}

@Composable
fun SecondarySmallSolidButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = primaryBlue,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.filledTonalButtonColors().copy(
            disabledContainerColor = color.copy(alpha = .05f),
            containerColor =  color.copy(alpha = .2f),
            contentColor = color
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview
@Composable
fun SecondarySmallSolidButtonPreview() {
    Surface(
        color = background
    ) {
        SecondarySmallSolidButton(
            text = "Hello",
            onClick = {}
        )
    }
}

@Composable
fun TopBarIconButton(
    onClick: () -> Unit,
    icon: ImageVector = Icons.AutoMirrored.Default.ArrowBack,
    enabled: Boolean = true,
) {
    IconButton(
        onClick = onClick,
        enabled = enabled,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun TopBarIconButtonPreview() {
    Surface(
        color = background
    ) {
        TopBarIconButton(
            onClick = {}
        )
    }
}

@Composable
fun SolidIconButton(
    onClick: () -> Unit,
    icon: ImageVector = Icons.Default.Done,
) {
    Card (
        onClick = onClick,
        colors = CardDefaults.cardColors().copy(
            containerColor = primaryBlue,
            contentColor = background
        )
    ) {
        Box(
            modifier = Modifier.size(56.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun SolidIconButtonPreview() {
    SolidIconButton(
        onClick = {}
    )
}
