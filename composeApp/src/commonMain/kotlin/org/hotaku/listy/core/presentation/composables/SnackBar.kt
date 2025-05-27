package org.hotaku.listy.core.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import org.hotaku.listy.core.presentation.background
import org.hotaku.listy.core.presentation.gray
import org.hotaku.listy.core.presentation.grayText
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SnackBar(
    message: String,
    actionText: String? = null,
    onDismiss: () -> Unit = {},
) {
    Snackbar(
        modifier = Modifier.shadow(
            elevation = 20.dp,
            shape = RoundedCornerShape(24.dp),
            ambientColor = gray
        ),
        action = actionText?.let {
            {
                SecondarySmallSolidButton(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = it,
                    onClick = onDismiss,
                )
            }
        },
        shape = RoundedCornerShape(20.dp),
        contentColor = grayText,
        containerColor = background,
        content = {
            Text(text = message)
        }
    )
}

@Preview
@Composable
private fun SnackBarPreview() {
    Box(
        modifier = Modifier.background(background).padding(24.dp)
    ) {
        SnackBar(
            message = "This is a snack bar message",
            actionText = "Dismiss",
            onDismiss = {}
        )
    }
}