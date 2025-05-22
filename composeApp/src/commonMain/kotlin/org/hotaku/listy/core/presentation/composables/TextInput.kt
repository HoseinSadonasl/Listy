package org.hotaku.listy.core.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import org.hotaku.listy.core.presentation.primaryBlue
import org.hotaku.listy.core.presentation.powder
import org.jetbrains.compose.ui.tooling.preview.Preview

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
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder?.let { { Text(text = placeholder) } },
        maxLines = maxLines,
        modifier = modifier,
        textStyle = textStyle.copy(
            lineHeight = TextUnit(
                30f,
                TextUnitType.Sp
            )
        ),
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors().copy(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = primaryBlue.copy(alpha = .1f),
            unfocusedContainerColor = primaryBlue.copy(alpha = .1f),
            disabledIndicatorColor = primaryBlue,
            disabledContainerColor = primaryBlue.copy(alpha = .1f),
        ),
        shape = RoundedCornerShape(16.dp)
    )
}

@Preview
@Composable
fun TextInputPreview() {
    Column (
        modifier = Modifier
            .background(powder)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextInput(
            value = "Test",
            onValueChange = {},
            placeholder = "Placeholder",
            modifier = Modifier,
        )

        TextInput(
            maxLines = 3,
            value = "Test Line 1 \nTest Line 2 \nTest Line 3",
            onValueChange = {},
            placeholder = "Placeholder",
            modifier = Modifier
                .defaultMinSize(minHeight = 112.dp)
        )

    }
}