package org.hotaku.listy.core.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.hotaku.listy.core.presentation.background
import org.hotaku.listy.core.presentation.brightBackgroundGray
import org.hotaku.listy.core.presentation.brightBackgroundGrayFocused
import org.hotaku.listy.core.presentation.grayText
import org.hotaku.listy.core.presentation.grayTextUnFocused
import org.jetbrains.compose.ui.tooling.preview.Preview

//@Composable
//fun TextInput(
//    modifier: Modifier = Modifier,
//    value: String,
//    textStyle: TextStyle = LocalTextStyle.current,
//    maxLines: Int = 1,
//    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
//    onValueChange: (String) -> Unit,
//    placeholder: String? = null,
//) {
//
//    DefaultCard(
//        cardColor = brightBackgroundGrayUnFocused
//    ) {
//        BasicTextField(
//            value = value,
//            onValueChange = onValueChange,
//            modifier = modifier.fillMaxWidth().padding(16.dp),
//            textStyle = textStyle.copy(
//                fontSize = TextUnit(14f, TextUnitType.Sp),
//                lineHeight = TextUnit(30f, TextUnitType.Sp)
//            ),
//            keyboardOptions = keyboardOptions,
//            singleLine = maxLines == 1,
//            decorationBox = { innerTextField ->
//                if (value.isEmpty() && placeholder != null) {
//                    BodyText(
//                        text = placeholder,
//                        color = grayTextUnFocused
//                    )
//                } else {
//                    BodyText(
//                        text = value,
//                        color = grayText
//                    )
//                }
//            }
//        )
//    }
//
//
////    TextField(
////        value = value,
////        onValueChange = onValueChange,
////        placeholder = placeholder?.let { { Text(text = placeholder) } },
////        maxLines = maxLines,
////        modifier = modifier,
////        textStyle = textStyle.copy(
////            fontSize = TextUnit(14f, TextUnitType.Sp),
////            lineHeight = TextUnit(
////                30f,
////                TextUnitType.Sp
////            )
////        ),
////        keyboardOptions = keyboardOptions,
////        colors = TextFieldDefaults.colors().copy(
////            focusedTextColor = grayText,
////            unfocusedTextColor = grayTextUnFocused,
////            disabledTextColor = brightGray,
////            focusedPlaceholderColor = gray,
////            focusedIndicatorColor = Color.Transparent,
////            unfocusedIndicatorColor = Color.Transparent,
////            focusedContainerColor = brightBackgroundGray,
////            unfocusedContainerColor = brightBackgroundGrayUnFocused,
////            disabledContainerColor = brightBackgroundGray,
////        ),
////        shape = RoundedCornerShape(16.dp)
////    )
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(
    value: String,
    onValueChange: (String) -> Unit,
    maxLines: Int = 1,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    isError: Boolean = false,
    singleLine: Boolean = true,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    val colors: TextFieldColors =
        TextFieldDefaults.colors().copy(
            unfocusedContainerColor = brightBackgroundGray,
            focusedContainerColor = brightBackgroundGrayFocused,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )

    BasicTextField(
        value = value,
        modifier =
            modifier
                .defaultMinSize(
                    minWidth = TextFieldDefaults.MinWidth,
                    minHeight = 48.dp,
                ),
        onValueChange = onValueChange,
        enabled = true,
        textStyle = LocalTextStyle.current.copy(
            fontSize = 14.sp,
            lineHeight = 30.sp,
            color = grayText,
            textAlign = TextAlign.Start,
        ),
        cursorBrush = SolidColor(grayText),
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = KeyboardActions.Default,
        interactionSource = interactionSource,
        singleLine = singleLine,
        maxLines = if (singleLine) 1 else Int.MAX_VALUE,
        minLines = maxLines,
        decorationBox = { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                visualTransformation = VisualTransformation.None,
                innerTextField = innerTextField,
                placeholder = {
                    BodyText(
                        text = placeholder.orEmpty(),
                        color = if (value.isEmpty()) grayTextUnFocused else grayText,
                    )
                },
                shape = RoundedCornerShape(24.dp),
                singleLine = singleLine,
                enabled = true,
                isError = isError,
                contentPadding = PaddingValues(16.dp),
                interactionSource = interactionSource,
                colors = colors,
            )
        },
    )

}

@Preview
@Composable
fun TextInputPreview() {
    Column(
        modifier = Modifier
            .background(background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextInput(
            value = "",
            onValueChange = {},
            placeholder = "Placeholder",
            modifier = Modifier,
        )

        TextInput(
            maxLines = 3,
            value = "Test Line 1 \nTest Line 2 \nTest Line 3",
            onValueChange = {},
            singleLine = false,
            placeholder = "Placeholder",
            modifier = Modifier
                .defaultMinSize(minHeight = 112.dp)
        )

    }
}