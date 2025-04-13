package org.hotaku.listy.core.presentation.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import org.hotaku.listy.core.presentation.brightGreen

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = LocalTextStyle.current,
    maxLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder?.let { { Text(text = placeholder) } },
        maxLines = maxLines,
        modifier = modifier,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        colors = OutlinedTextFieldDefaults.colors().copy(
            focusedIndicatorColor = brightGreen
        ),
        shape = RoundedCornerShape(percent = 16)
    )
}