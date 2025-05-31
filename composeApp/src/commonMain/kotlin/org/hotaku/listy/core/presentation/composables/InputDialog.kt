package org.hotaku.listy.core.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.alert_dialog_confirm
import listy.composeapp.generated.resources.alert_dialog_dismiss
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun InputDialog(
    title: String,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    confirmButtonText: String? = null,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        DefaultCard(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            VerticalSpacer_20dp()
            TitleText(
                text = title,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                bold = true,
            )
            VerticalSpacer_12dp()
            TextInput(
                value = value,
                onValueChange = onValueChange,
                placeholder = placeholder,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
            VerticalSpacer_12dp()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SecondarySmallSolidButton(
                    text =  stringResource(Res.string.alert_dialog_dismiss),
                    onClick = {
                        onDismissRequest()
                    },
                )
                HorizontalSpacer_8dp()
                SmallSolidButton(
                    text = confirmButtonText ?: stringResource(Res.string.alert_dialog_confirm),
                    onClick = {
                        onConfirm()
                        onDismissRequest()
                    },
                )
            }
            VerticalSpacer_20dp()
        }
    }
}

@Preview
@Composable
fun InputDialogPreview() {
    InputDialog(
        title = "Input Dialog",
        value = "",
        placeholder = "Type something...",
        onValueChange = {},
        confirmButtonText = "OK",
        onConfirm = {},
        onDismissRequest = {}
    )
}