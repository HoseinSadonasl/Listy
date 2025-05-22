package org.hotaku.listy.core.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.alert_dialog_confirm
import listy.composeapp.generated.resources.alert_dialog_dismiss
import org.hotaku.listy.core.presentation.brightPink
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AlertDialog(
    title: String,
    message: String? = null,
    confirmButtonText: String? = null,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        DefaultCard(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
            )
            message?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SecondarySolidButton(
                    modifier = Modifier
                        .weight(1f),
                    text = confirmButtonText ?: stringResource(Res.string.alert_dialog_confirm),
                    color = brightPink,
                    onClick = {
                        onConfirm()
                        onDismissRequest()
                    },
                )
                HorizontalSpacer_16dp()
                SolidButton(
                    modifier = Modifier
                        .weight(1f),
                    text =  stringResource(Res.string.alert_dialog_dismiss),
                    onClick = {
                        onDismissRequest()
                    },
                )
            }
        }
    }
}
@Preview
@Composable
fun AlertDialogPreview() {
    AlertDialog(
        title = "Title",
        message = "This is an alert dialog",
        confirmButtonText = "OK",
        onConfirm = {},
        onDismissRequest = {}
    )
}