package org.hotaku.listy.core.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.hotaku.listy.core.presentation.background
import org.hotaku.listy.core.presentation.grayText
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HeaderText(
    modifier: Modifier = Modifier,
    text: String,
    maxLine: Int = 1,
    bold: Boolean = false,
    align: TextAlign = TextAlign.Start,
    color: Color = grayText,
    textDecoration: TextDecoration? = null,
) {
    BasicText(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontSize = 24.sp,
            color = color,
            textAlign = align,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
            textDecoration = textDecoration
        ),
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLine,
    )
}

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    text: String,
    maxLine: Int = 1,
    bold: Boolean = false,
    align: TextAlign = TextAlign.Start,
    color: Color = grayText,
    textDecoration: TextDecoration? = null,
) {
    BasicText(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontSize = 20.sp,
            color = color,
            textAlign = align,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
            textDecoration = textDecoration
        ),
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLine,
    )
}

@Composable
fun BodyText(
    modifier: Modifier = Modifier,
    text: String,
    maxLine: Int = Int.MAX_VALUE,
    bold: Boolean = false,
    align: TextAlign = TextAlign.Start,
    color: Color = grayText,
    textDecoration: TextDecoration? = null,
) {
    BasicText(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontSize = 16.sp,
            color = color,
            textAlign = align,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
            textDecoration = textDecoration
        ),
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLine,
    )
}

@Composable
fun CaptionText(
    modifier: Modifier = Modifier,
    text: String,
    maxLine: Int = 1,
    bold: Boolean = false,
    align: TextAlign = TextAlign.Start,
    color: Color = grayText,
    textDecoration: TextDecoration? = null,
) {
    BasicText(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontSize = 12.sp,
            color = color,
            textAlign = align,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
            textDecoration = textDecoration
        ),
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLine,
    )
}

@Composable
fun LabelText(
    modifier: Modifier = Modifier,
    text: String,
    bold: Boolean = false,
    align: TextAlign = TextAlign.Start,
    color: Color = grayText,
) {
    BasicText(
        modifier = modifier,
        text = text,
        style = TextStyle(
            fontSize = 12.sp,
            color = color,
            textAlign = align,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Medium,
        )
    )
}

@Preview
@Composable
private fun TextPreview() {
    Column(
        modifier = Modifier
            .background(color = background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HeaderText(text = "Header Text")
        TitleText(text = "Title Text")
        BodyText(text = "Body Text")
        CaptionText(text = "Caption Text")
        LabelText(text = "Label Text")
    }
}